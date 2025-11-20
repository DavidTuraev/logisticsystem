package ru.dsacademy.logisticsystem.exception.api;
import ru.dsacademy.logisticsystem.exception.ControllerAdvice;
import java.time.Instant;
import java.util.List;
/**
 * Класс для хранения ошибки. Используется в {@link ControllerAdvice}
 */
public record ApiError(
        String message,
        List<FieldViolation> violations,
        String path,
        Instant timestamp) {
    /**
     * Статичный метод для создания Объекта ApiError.
     * @return ApiError
     */
    public static ApiError of(String message, List<FieldViolation> violations, String path) {
        return new ApiError(message, violations, path, Instant.now());
    }
}

