package ru.dsacademy.logisticsystem.service;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.dsacademy.logisticsystem.dto.cargo.CargoResponseDto;
import ru.dsacademy.logisticsystem.dto.loading.LoadingRequestDto;
import ru.dsacademy.logisticsystem.dto.loading.LoadingResponseDto;
import ru.dsacademy.logisticsystem.dto.truck.TruckResponseDto;
import ru.dsacademy.logisticsystem.entity.Cargo;
import ru.dsacademy.logisticsystem.entity.Truck;
import ru.dsacademy.logisticsystem.exception.custom.DuplicateItemException;
import ru.dsacademy.logisticsystem.exception.custom.TruckNotFoundException;
import ru.dsacademy.logisticsystem.repository.CargoRepo;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
/**
 * Сервис для загрузки грузов в грузовик.
 * Осуществляет проверку на дубли в списке грузов, загрузку груза по жадному алгоритму и вычисление общего веса,
 * объема и стоимости загруженных грузов.
 */
@Service
@RequiredArgsConstructor
public class LoadingService {
    private final TruckService truckService;
    private final CargoRepo cargoRepo;
    /**
     * Загружает грузы в транспортное средство, проверяя на дубли и вычисляя общие параметры для загруженных грузов.
     * @param request Объект с данными о загрузке, включая ID грузовика и ID грузов.
     * @return Объект {@link LoadingResponseDto} с результатами загрузки
     * @throws DuplicateItemException   Если в списке грузов есть дубликаты.
     * @throws IllegalArgumentException Если поле request.truckId() = null
     * @throws TruckNotFoundException   Если не найден грузовик по id
     */
    public LoadingResponseDto load(LoadingRequestDto request) {
        TruckResponseDto dto = truckService.getTruckById(request.truckId());
        if (request.cargoIds().isEmpty()) {
            return new LoadingResponseDto(request.truckId(), null, 0.0, 0.0, 0.0);
        }
        if (hasDuplicates(request.cargoIds())) {
            throw new DuplicateItemException("В списке грузов есть дубликаты");
        }
        Truck truck = new Truck(dto.id(), dto.maxWeight(), dto.maxVolume());
        List<Cargo> cargoList = cargoRepo.findAllById(request.cargoIds());
        List<Cargo> loaded = loadCargoGreedy(cargoList, truck);
        double usedWeight = loaded.stream().mapToDouble(Cargo::getWeight).sum();
        double usedVolume = loaded.stream().mapToDouble(Cargo::getVolume).sum();
        double totalPrice = loaded.stream().mapToDouble(Cargo::getPrice).sum();
        List<CargoResponseDto> loadDtoCargos = loaded.stream().map(c -> new CargoResponseDto(c.getId(), c.getName(), c.getWeight(), c.getVolume(), c.getPrice())).toList();
        return new LoadingResponseDto(truck.getId(), loadDtoCargos, usedWeight, usedVolume, totalPrice);
    }
    /**
     * Проверяет, содержит ли список идентификаторов грузов дубликаты.
     * @param cargoIds Список идентификаторов грузов.
     * @return {@code true}, если в списке есть дубликаты, {@code false} — если нет.
     */
    private boolean hasDuplicates(List<Long> cargoIds) {
        Set<Long> uniqueIds = new HashSet<>(cargoIds);
        return uniqueIds.size() < cargoIds.size();
    }
    /**
     * Загружает грузы в транспортное средство по жадному алгоритму, выбирая те, которые имеют наибольшую стоимость на единицу
     * веса и объема, и проверяет, помещаются ли они в грузовик по весу и объему.
     * @param cargoList Список доступных для загрузки грузов.
     * @param truck Грузовик, в который нужно загрузить грузы.
     * @return Список грузов, которые были загружены в транспортное средство.
     */
    private List<Cargo> loadCargoGreedy(List<Cargo> cargoList, Truck truck) {
        cargoList.sort((c1, c2) -> Double.compare(c2.getPrice() / (c2.getWeight() + c2.getVolume()), c1.getPrice() / (c1.getWeight() + c1.getVolume())));
        List<Cargo> loaded = new ArrayList<>();
        double usedWeight = 0;
        double usedVolume = 0;
        for (Cargo cargo : cargoList) {
            boolean fitsByWeight = usedWeight + cargo.getWeight() <= truck.getMaxWeight();
            boolean fitsByVolume = usedVolume + cargo.getVolume() <= truck.getMaxVolume();
            if (fitsByWeight && fitsByVolume) {
                loaded.add(cargo);
                usedWeight += cargo.getWeight();
                usedVolume += cargo.getVolume();
            }
        }
        return loaded;
    }
}
