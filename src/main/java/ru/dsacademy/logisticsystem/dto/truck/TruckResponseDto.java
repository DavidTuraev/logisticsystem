package ru.dsacademy.logisticsystem.dto.truck;
/**
 * DTO для ответа на запрос о грузовике.
 * @param id id грузовика.
 * @param maxWeight Максимальный вес, который грузовик может перевозить.
 * @param maxVolume Максимальный объем, который грузовик может перевозить.
 */
public record TruckResponseDto(
        Long id,
        Long maxWeight,
        Long maxVolume) {
}
