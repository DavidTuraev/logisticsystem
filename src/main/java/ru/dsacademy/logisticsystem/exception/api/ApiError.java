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
     * @param message Сообщение об ошибке, которое будет отображено в ответе.
     * @param violations Список нарушений валидации, содержащий поля с ошибками и их описание.
     * @param path Путь запроса (URI), который вызвал ошибку.
     * @return Новый объект {@link ApiError} с текущим временем ошибки.
     */
    public static ApiError of(String message, List<FieldViolation> violations, String path) {
        return new ApiError(message, violations, path, Instant.now());
    }
}

