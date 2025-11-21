package ru.dsacademy.logisticsystem.exception.custom;
/**
 * Исключение, которое выбрасывается, когда возникает ошибка из-за дублирования элемента.
 */
public class DuplicateItemException extends RuntimeException {
    /**
     * Конструктор исключения с заданным сообщением.
     * @param message сообщение об ошибке
     */
    public DuplicateItemException(String message) {
        super(message);
    }
}
