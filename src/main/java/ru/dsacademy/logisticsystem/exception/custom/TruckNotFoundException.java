package ru.dsacademy.logisticsystem.exception.custom;
import lombok.Getter;
/**
 * Исключение, которое выбрасывается, когда грузовик не найден.
 */
@Getter
public class TruckNotFoundException extends RuntimeException {
    private final String field;
    private final String value;
    /**
     * Конструктор исключения с заданным сообщением, полем и значением.
     * @param message сообщение об ошибке
     * @param field поле, по которому не был найден грузовик.
     * @param value значение поля, по которому не был найден грузовик.
     */
    public TruckNotFoundException(String message, String field, String value) {
        super(message);
        this.field = field;
        this.value = value;
    }
}
