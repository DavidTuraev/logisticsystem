package ru.dsacademy.logisticsystem.dto;
import java.util.List;
/**
 * DTO для получения результата вычисления алгоритма поиска оптимальных путей.
 */
public record RouteResult(
        long distance,
        List<Long> path
) {
}
