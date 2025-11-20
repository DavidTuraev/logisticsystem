package ru.dsacademy.logisticsystem.service.pathfinder;
import org.springframework.stereotype.Service;
import ru.dsacademy.logisticsystem.util.graph.Edge;
import ru.dsacademy.logisticsystem.util.graph.Vertex;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
/**
 * Сервис для поиска кратчайших путей между городами с помощью алгоритма Дейкстры.
 */
@Service
public class DijkstraShortestPathFinderService implements ShortestPathFinderService {
    private final PriorityQueue<Vertex> queue = new PriorityQueue<>();
    /**
     * Выполняет алгоритм Дейкстры для указанной стартовой вершины.
     *
     * @param start исходная вершина графа.
     */
    @Override
    public void compute(Vertex start) {
        start.setDistance(0L);
        queue.add(start);
        while (!queue.isEmpty()) {
            Vertex curr = queue.poll();
            for (Edge edgeNeighbour : curr.getNeighbors()) {
                Vertex vertexNeighbour = edgeNeighbour.getTo();
                long updateDistance = curr.getDistance() + edgeNeighbour.getWeight();
                if (updateDistance < vertexNeighbour.getDistance()) {
                    vertexNeighbour.setDistance(updateDistance);
                    vertexNeighbour.setPrevious(curr);
                    queue.add(vertexNeighbour);
                }
            }
        }
    }
    /**
     * Метод, который возвращает оптимальный путь для определенной вершины.
     * Вызывается после того, как выполнился метод compute.
     *
     * @param end Поле для которого нужно узнать путь от начала до вершины end.
     * @return возвращается список id городов в порядке прохождения маршрута от начала до конца.
     */
    @Override
    public List<Long> getPath(Vertex end) {
        List<Long> result = new ArrayList<>();
        Vertex curr = end;
        while (curr != null) {
            result.add(curr.getId());
            curr = curr.getPrevious();
        }
        Collections.reverse(result);
        return result;
    }
}
