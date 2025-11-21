package ru.dsacademy.logisticsystem.dto.truck;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
/**
 * DTO для запроса на создание или обновление данных о грузовике.
 * @param maxWeight Максимальный вес, который грузовик может перевозить. Должен быть положительным и не может быть null.
 * @param maxVolume Максимальный объем, который грузовик может перевозить. Должен быть положительным и не может быть null.
 */
public record TruckRequestDto(
        @Positive
        @NotNull
        Long maxWeight,
        @Positive
        @NotNull
        Long maxVolume) {
}
