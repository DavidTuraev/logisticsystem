package ru.dsacademy.logisticsystem.dto.cargo;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
/**
 * DTO для запроса груза. Используется в качестве передачи данных.
 * @param name Название груза. Не может быть null.
 * @param weight Вес груза. Должен быть положительным и не может быть null.
 * @param volume Объем груза. Должен быть положительным и не может быть null.
 * @param price Цена груза. Должна быть положительной и не может быть null.
 */
public record CargoRequestDto(
        @NotNull
        String name,
        @Positive
        @NotNull
        Double weight,
        @Positive
        @NotNull
        Double volume,
        @Positive
        @NotNull
        Double price) {
}
