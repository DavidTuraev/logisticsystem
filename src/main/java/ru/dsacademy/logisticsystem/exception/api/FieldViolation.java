package ru.dsacademy.logisticsystem.exception.api;
/**
 * Вспомогательный класс для создания ApiError.
 * Определяет поле и почему поле не прошло проверку*/
public record FieldViolation(
        String field,
        String reason) {
}
