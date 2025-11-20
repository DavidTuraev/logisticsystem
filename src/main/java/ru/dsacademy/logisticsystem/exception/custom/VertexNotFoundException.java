package ru.dsacademy.logisticsystem.exception.custom;
import lombok.Getter;
/**
 * Исключение, которое создается, когда добавляются пути в граф и вершины не найдены в графе.
 */
@Getter
public class VertexNotFoundException extends RuntimeException {
    private final String valueFrom;
    private final String valueTo;
    /**
     * Конструктор для создания исключения.
     *
     * @param message   Сообщение об ошибке
     * @param valueFrom id вершина начала
     * @param valueTo   id вершина конца
     */
    public VertexNotFoundException(String message, String valueFrom, String valueTo) {
        super(message);
        this.valueFrom = valueFrom;
        this.valueTo = valueTo;
    }
}
