package ru.dsacademy.logisticsystem.dto.loading;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.util.List;
/**
 * DTO для запроса на загрузку.
 * @param truckId id грузовика в который загружают грузы. Должен быть положительным и не может быть null.
 * @param cargoIds Список id грузов, которые загружаются в указанный грузовик. Не может быть null.
 */
public record LoadingRequestDto(
        @Positive
        @NotNull
        Long truckId,
        @NotNull
        List<Long> cargoIds) {
}
