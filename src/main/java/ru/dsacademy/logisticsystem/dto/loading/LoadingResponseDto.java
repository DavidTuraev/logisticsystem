package ru.dsacademy.logisticsystem.dto.loading;
import ru.dsacademy.logisticsystem.dto.cargo.CargoResponseDto;
import ru.dsacademy.logisticsystem.entity.Cargo;
import java.util.List;
/**
 * DTO для ответа на запрос о загрузке.
 * @param truckId id грузовика, в который были загружены грузы.
 * @param loadedCargo Список загруженных грузов, представленных объектами типа {@link Cargo}.
 * @param usedWeight Использованный вес из максимальной грузоподъемности грузовика.
 * @param usedVolume Использованный объем из максимальной вместимости грузовика.
 * @param totalPrice Общая цена всех загруженных грузов.
 */
public record LoadingResponseDto(
        Long truckId,
        List<CargoResponseDto> loadedCargo,
        Double usedWeight,
        Double usedVolume,
        Double totalPrice) {
}
