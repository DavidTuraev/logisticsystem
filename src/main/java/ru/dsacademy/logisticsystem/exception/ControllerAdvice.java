package ru.dsacademy.logisticsystem.exception;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.dsacademy.logisticsystem.exception.api.ApiError;
import ru.dsacademy.logisticsystem.exception.api.FieldViolation;
import ru.dsacademy.logisticsystem.exception.custom.RouteNotFoundException;
import ru.dsacademy.logisticsystem.exception.custom.VertexNotFoundException;
import ru.dsacademy.logisticsystem.util.graph.Graph;
import java.util.List;
/**
 * Глобальный набор исключений REST контроллера.
 * Класс перехватывает исключения возникающие в процессе обработки HTTP запросов.
 * Все исключения возвращают ResponseEntity<{@link ApiError}> в котором есть
 * сообщение об ошибке, список полей которые не прошли проверку, URI контроллера в котором произошла ошибка.
 */
@RestControllerAdvice
public class ControllerAdvice {
    /**
     * Исключение показывает, что маршрут не найден в графе.
     * @return ResponseEntity<{@link ApiError}>
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
     * @return ResponseEntity<{@link ApiError}>
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
}
