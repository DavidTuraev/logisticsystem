package ru.dsacademy.logisticsystem.service;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.dsacademy.logisticsystem.dto.truck.TruckRequestDto;
import ru.dsacademy.logisticsystem.dto.truck.TruckResponseDto;
import ru.dsacademy.logisticsystem.entity.Truck;
import ru.dsacademy.logisticsystem.exception.custom.TruckNotFoundException;
import ru.dsacademy.logisticsystem.repository.TruckRepo;
@RequiredArgsConstructor
@Service
public class TruckService {
    private final TruckRepo truckRepository;
    /**
     * Добавляет новый грузовик в базу данных.
     * @param truck новый грузовик
     * @return сохранённый грузовик
     * @throws IllegalArgumentException исключение если truck = null
     */
    public TruckResponseDto addTruck(TruckRequestDto truck) {
        if (truck == null) {
            throw new IllegalArgumentException("Поле truck равно null");
        }
        Truck entity = new Truck();
        entity.setMaxWeight(truck.maxWeight());
        entity.setMaxVolume(truck.maxVolume());
        Truck response = truckRepository.save(entity);
        return new TruckResponseDto(response.getId(),
                response.getMaxWeight(), response.getMaxVolume());
    }
    /**
     * Получает грузовик по его Id.
     * @param id идентификатор грузовика
     * @return грузовик с заданным Id
     * @throws TruckNotFoundException если грузовик с таким Id не найден
     */
    public TruckResponseDto getTruckById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Поле id равно null");
        }
        Truck entity = truckRepository.findById(id)
                .orElseThrow(() -> new TruckNotFoundException("Грузовик не найден", "id", id.toString()));
        return new TruckResponseDto(entity.getId(),
                entity.getMaxWeight(), entity.getMaxVolume());
    }
}
