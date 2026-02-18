package ru.dsacademy.logisticsystem.exception.api;

/**
 * Вспомогательный класс для создания ApiError.
 * Определяет поле и почему поле не прошло проверку
 *
 * @param field  поле по которому произошла ошибка
 * @param reason описание ошибки
 */
public record FieldViolation(String field, String reason) {
}
