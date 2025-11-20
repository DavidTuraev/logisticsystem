package ru.dsacademy.logisticsystem.service.pathfinder;
import ru.dsacademy.logisticsystem.util.graph.Vertex;
import java.util.List;
/**
 * Интерфейс для вычисления кратчайших путей в графе.
 */
public interface ShortestPathFinderService {
    /**
     * Вычисляет кратчайшие пути от стартовой вершины до всех достижимых вершин графа.
     *
     * @param start стартовая вершина
     */
    void compute(Vertex start);
    /**
     * Возвращает кратчайший путь к указанной конечной вершине в виде списка идентификаторов.
     * @param end конечная вершина до которой нужно получить кратчайший путь
     * @return список идентификаторов вершин, предоставляющий кратчайший путь до конечной вершины
     */
    List<Long> getPath(Vertex end);
}
