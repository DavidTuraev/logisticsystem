package ru.dsacademy.logisticsystem.dto.route;
import java.util.List;
/**
 * DTO для получения результата вычисления алгоритма поиска оптимальных путей.
 * @param distance Расстояние маршрута в километрах.
 * @param path Список идентификаторов вершин (городов, пунктов) маршрута в порядке следования.
 */
public record RouteResponseDto(
        Long distance,
        List<String> path
) {
}
