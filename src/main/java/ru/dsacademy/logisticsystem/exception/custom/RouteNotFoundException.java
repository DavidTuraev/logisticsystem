package ru.dsacademy.logisticsystem.exception.custom;
import lombok.Getter;
/**
 * Исключение создается, когда путь между двумя городами не найден.
 */
@Getter
public class RouteNotFoundException extends RuntimeException {
    private final String field1;
    private final String value1;
    private final String field2;
    private final String value2;
    /**
     * Конструктор для создания исключения.
     * @param message Сообщение исключения
     * @param field1 поле которое не прошло проверку
     * @param value1 значение для field1
     * @param field2 поле которое не прошло проверку
     * @param value2 значение для field2
     */
    public RouteNotFoundException(String message, String field1, String value1, String field2, String value2) {
        super(message);
        this.field1 = field1;
        this.value1 = value1;
        this.field2 = field2;
        this.value2 = value2;
    }
}
