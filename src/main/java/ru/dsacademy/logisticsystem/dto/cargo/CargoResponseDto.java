package ru.dsacademy.logisticsystem.dto.cargo;
/**
 * DTO для ответа о грузе.
 * @param id Уникальный идентификатор груза.
 * @param name Название груза.
 * @param weight Вес груза.
 * @param volume Объем груза.
 * @param price Цена груза.
 */
public record CargoResponseDto(
        Long id,
        String name,
        Double weight,
        Double volume,
        Double price) {
}
