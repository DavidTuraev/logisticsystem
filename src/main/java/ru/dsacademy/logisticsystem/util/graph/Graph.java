package ru.dsacademy.logisticsystem.util.graph;
import ru.dsacademy.logisticsystem.exception.custom.VertexNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * Представляет граф городов для расчета оптимальных маршрутов.
 * Хранит вершины и связи между ними в виде.
 */
public class Graph implements GraphView {
    private final Map<Long, Vertex> vertexMap = new HashMap<>();
    /**
     * Метод для получения вершины по id из vertexMap(граф).
     *
     * @param id - id города
     * @return Vertex - объект вершины, с которым работает алгоритм Дейкстры
     * или {@code null} если вершина отсутствует.
     */
    @Override
    public Vertex get(long id) {
        if (!vertexMap.containsKey(id)) {
            return null;
        }
        return vertexMap.get(id);
    }
    /**
     * Получение всех вершин из vertexMap(граф).
     */
    @Override
    public List<Vertex> getAllVertexes() {
        return vertexMap.values().stream().toList();
    }
    /**
     * Метод для добавления вершины в vertexMap(граф).
     *
     * @param id - id города.
     */
    void addVertex(Long id) {
        vertexMap.put(id, new Vertex(id));
    }
    /**
     * Метод для добавления маршрутов(ребер) в vertexMap(граф)
     *
     * @param fromId - id начальной вершины(города)
     * @param toId   - id конечной вершины(города)
     * @param weight - вес ребра (дистанция между городами)
     * @throws VertexNotFoundException исключение, если вершины не найдены в vertexMap.
     */
    void addEdge(long fromId, long toId, long weight) {
        Vertex vertexFrom = vertexMap.get(fromId);
        Vertex vertexTo = vertexMap.get(toId);
        if (vertexFrom == null || vertexTo == null) {
            throw new VertexNotFoundException("Вершины не найдены",
                    String.valueOf(fromId), String.valueOf(toId));
        }
        vertexFrom.addNeighbor(new Edge(vertexTo, weight));
        vertexTo.addNeighbor(new Edge(vertexFrom, weight));
    }
}
