package ru.dsacademy.logisticsystem.service;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.dsacademy.logisticsystem.dto.cargo.CargoRequestDto;
import ru.dsacademy.logisticsystem.dto.cargo.CargoResponseDto;
import ru.dsacademy.logisticsystem.entity.Cargo;
import ru.dsacademy.logisticsystem.exception.custom.CargoNotFoundException;
import ru.dsacademy.logisticsystem.repository.CargoRepo;
import java.util.List;
/**
 * Сервис для взаимодействия с объектом Cargo из базы данных.
 */
@RequiredArgsConstructor
@Service
public class CargoService {
    private final CargoRepo cargoRepository;
    /**
     * Получает список всех грузов в базе данных.
     * @return список всех грузов
     */
    public List<Cargo> getAllCargo() {
        return cargoRepository.findAll();
    }
    /**
     * Получает груз по его Id.
     * @param id идентификатор груза
     * @return груз с заданным Id
     * @throws CargoNotFoundException если груз с таким Id не найден
     */
    public CargoResponseDto getCargoById(Long id) {
        Cargo cargo = cargoRepository.findById(id).orElseThrow(() -> new CargoNotFoundException("Груз не найден", "id", id.toString()));
        return new CargoResponseDto(cargo.getId(), cargo.getName(), cargo.getWeight(), cargo.getVolume(), cargo.getPrice());
    }
    /**
     * Добавляет новый груз в базу данных.
     * @param cargoDto новый груз
     * @return сохранённый груз
     * @throws IllegalArgumentException если данные груза неверны или некорректны
     */
    public CargoResponseDto addCargo(CargoRequestDto cargoDto) {
        if (cargoDto == null) {
            throw new IllegalArgumentException("Поле cargoDto равно null");
        }
        Cargo cargo = new Cargo();
        cargo.setName(cargoDto.name());
        cargo.setPrice(cargoDto.price());
        cargo.setWeight(cargoDto.weight());
        cargo.setVolume(cargoDto.volume());
        Cargo response = cargoRepository.save(cargo);
        return new CargoResponseDto(response.getId(), response.getName(), response.getWeight(), response.getVolume(), response.getPrice());
    }
}
