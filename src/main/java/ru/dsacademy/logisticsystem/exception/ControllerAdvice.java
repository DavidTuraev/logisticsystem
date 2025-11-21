package ru.dsacademy.logisticsystem.exception;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import ru.dsacademy.logisticsystem.exception.api.ApiError;
import ru.dsacademy.logisticsystem.exception.api.FieldViolation;
import ru.dsacademy.logisticsystem.exception.custom.*;
import ru.dsacademy.logisticsystem.util.graph.Graph;
import java.util.List;
/**
 * Глобальный набор обработки исключений REST контроллера.
 * Класс перехватывает исключения возникающие в процессе обработки HTTP запросов.
 * Все исключения возвращают ResponseEntity<{@link ApiError}> в котором есть
 * сообщение об ошибке, список полей которые не прошли проверку, URI контроллера в котором произошла ошибка.
 */
@RestControllerAdvice
public class ControllerAdvice {
    /**
     * Исключение показывает, что маршрут не найден в графе.
     * @param e исходное исключение
     * @param req Объект {@link HttpServletRequest}, используемый для получения URI запроса.
     * @return {@link ResponseEntity}, содержащий объект {@link ApiError} с ошибкой и статусом "400 Bad Request".
     */
    @ExceptionHandler(RouteNotFoundException.class)
    public ResponseEntity<ApiError> handleRouteNotFoundException(RouteNotFoundException e, HttpServletRequest req) {
        ApiError apiError = ApiError.of(
                e.getMessage(),
                List.of(new FieldViolation(e.getField1(), e.getValue1()),
                        new FieldViolation(e.getField2(), e.getValue2())),
                req.getRequestURI()
        );
        return ResponseEntity.badRequest().body(apiError);
    }
    /**
     * Исключение показывает, что не найдены вершины в графе {@link Graph}.
     * @param e исходное исключение
     * @param req Объект {@link HttpServletRequest}, используемый для получения URI запроса.
     * @return {@link ResponseEntity}, содержащий объект {@link ApiError} с ошибкой и статусом "400 Bad Request".
     */
    @ExceptionHandler(VertexNotFoundException.class)
    public ResponseEntity<ApiError> handleVertexNotFoundException(VertexNotFoundException e, HttpServletRequest req) {
        ApiError apiError = ApiError.of(
                e.getMessage(),
                List.of(new FieldViolation("", e.getValueFrom()), new FieldViolation("", e.getValueTo())),
                req.getRequestURI()
        );
        return ResponseEntity.badRequest().body(apiError);
    }
    /**
     * Исключение показывает, что не найден груз.
     * @param e исходное исключение
     * @param req Объект {@link HttpServletRequest}, используемый для получения URI запроса.
     * @return {@link ResponseEntity}, содержащий объект {@link ApiError} с ошибкой и статусом "400 Bad Request".
     */
    @ExceptionHandler(CargoNotFoundException.class)
    public ResponseEntity<ApiError> handleCargoNotFoundException(CargoNotFoundException e, HttpServletRequest req) {
        ApiError apiError = ApiError.of(
                e.getMessage(),
                List.of(new FieldViolation(e.getField(), "Значение не найдено: " + e.getValue())),
                req.getRequestURI()
        );
        return ResponseEntity.badRequest().body(apiError);
    }
    /**
     * Исключение показывает, что не найден грузовик.
     * @param e исходное исключение
     * @param req Объект {@link HttpServletRequest}, используемый для получения URI запроса.
     * @return {@link ResponseEntity}, содержащий объект {@link ApiError} с ошибкой и статусом "400 Bad Request".
     */
    @ExceptionHandler(TruckNotFoundException.class)
    public ResponseEntity<ApiError> handleTruckNotFoundException(TruckNotFoundException e, HttpServletRequest req) {
        ApiError apiError = ApiError.of(
                e.getMessage(),
                List.of(new FieldViolation(e.getField(), "неизвестный идентификатор: " + e.getValue())),
                req.getRequestURI()
        );
        return ResponseEntity.badRequest().body(apiError);
    }
    /**
     * Исключения, которые возникают при несоответствии аргументов метода ограничениям валидации.
     * Это исключение выбрасывается, когда параметры метода, аннотированные ограничениями
     * валидации, не проходят валидацию.
     * @param ex исключение, содержащее информацию о нарушениях валидации.
     * @param req Объект {@link HttpServletRequest}, используемый для получения URI запроса.
     * @return {@link ResponseEntity}, содержащий объект {@link ApiError} с ошибкой и статусом "400 Bad Request".
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex,
                                                                          HttpServletRequest req) {
        List<FieldViolation> violations = ex.getBindingResult()
                .getFieldErrors().stream()
                .map(err -> new FieldViolation(
                        err.getField(),
                        err.getDefaultMessage()
                ))
                .toList();
        ApiError apiError = ApiError.of(
                "Ошибка валидации",
                violations,
                req.getRequestURI()
        );
        return ResponseEntity.badRequest().body(apiError);
    }
    /**
     * Исключения, которые возникают, когда тип аргумента метода не соответствует ожидаемому типу.
     * @param ex исключение, содержащее информацию о несоответствии типа аргумента.
     * @param req объект {@link HttpServletRequest}, используемый для получения URI запроса.
     * @return {@link ResponseEntity}, содержащий объект {@link ApiError} с ошибкой и статусом "400 Bad Request".
     */
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ApiError> handleMethodArgumentNotValidException(MethodArgumentTypeMismatchException ex,
                                                                          HttpServletRequest req) {
        String param = ex.getName();
        Class<?> required = ex.getRequiredType();
        String requiredType = required != null ? required.getSimpleName() : "неизвестный";
        String reason = "Параметр '" + param + "' должен быть типа '" + requiredType + "'";
        List<FieldViolation> violations = List.of(
                new FieldViolation(param, reason)
        );
        ApiError apiError = ApiError.of(
                "Validation failed",
                violations,
                req.getRequestURI()
        );
        return ResponseEntity.badRequest().body(apiError);
    }
    /**
     * Исключения, которые возникают при нарушении ограничений, установленных для полей валидации.
     * @param ex исключение, содержащее информацию о нарушенных ограничениях.
     * @param req объект {@link HttpServletRequest}, используемый для получения URI запроса.
     * @return {@link ResponseEntity}, содержащий объект {@link ApiError} с ошибкой и статусом "400 Bad Request".
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ApiError> handleConstraintViolationException(
            ConstraintViolationException ex,
            HttpServletRequest req
    ) {
        List<FieldViolation> violations = ex.getConstraintViolations().stream()
                .map(violation -> new FieldViolation(
                        extractFieldName(violation.getPropertyPath().toString()),
                        violation.getMessage()
                ))
                .toList();
        ApiError apiError = ApiError.of(
                "Validation failed",
                violations,
                req.getRequestURI()
        );
        return ResponseEntity.badRequest().body(apiError);
    }
    /**
     * Исключение, возникающие при передаче некорректных аргументов в метод.
     * @param e исключение, содержащее сообщение об ошибке.
     * @param req объект {@link HttpServletRequest}, используемый для получения URI запроса.
     * @return {@link ResponseEntity}, содержащий объект {@link ApiError} с ошибкой и статусом "400 Bad Request".
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ApiError> handleIllegalArgumentException(IllegalArgumentException e, HttpServletRequest req) {
        ApiError apiError = ApiError.of(
                e.getMessage(),
                List.of(),
                req.getRequestURI()
        );
        return ResponseEntity.badRequest().body(apiError);
    }
    /**
     * Обрабатывает исключения {@link DuplicateItemException}, возникающие при добавлении дублирующихся элементов.
     * @param e исключение, содержащее сообщение об ошибке.
     * @param req объект {@link HttpServletRequest}, используемый для получения URI запроса.
     * @return {@link ResponseEntity}, содержащий объект {@link ApiError} с ошибкой и статусом "400 Bad Request".
     */
    @ExceptionHandler(DuplicateItemException.class)
    public ResponseEntity<ApiError> handleIllegalArgumentException(DuplicateItemException e, HttpServletRequest req) {
        ApiError apiError = ApiError.of(
                e.getMessage(),
                List.of(),
                req.getRequestURI()
        );
        return ResponseEntity.badRequest().body(apiError);
    }
    /**
     * Извлекает название поля из строки пути свойства (например, из "person.name" извлекается "name").
     * @param propertyPath путь к свойству в формате строки.
     * @return название поля из пути свойства.
     */
    private String extractFieldName(String propertyPath) {
        int dotIndex = propertyPath.lastIndexOf('.');
        return dotIndex != -1 ? propertyPath.substring(dotIndex + 1) : propertyPath;
    }
}
