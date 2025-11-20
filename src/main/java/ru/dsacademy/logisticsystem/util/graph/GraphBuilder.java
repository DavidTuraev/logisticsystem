package ru.dsacademy.logisticsystem.util.graph;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.dsacademy.logisticsystem.entity.City;
import ru.dsacademy.logisticsystem.entity.CityRoute;
import ru.dsacademy.logisticsystem.repository.CityRepo;
import ru.dsacademy.logisticsystem.repository.CityRouteRepo;
import java.util.List;
/**
 * Сервис предоставляет создание графа и его заполнение.
 */
@RequiredArgsConstructor
@Component
public class GraphBuilder {
    private final CityRepo cityRepo;
    private final CityRouteRepo cityRouteRepo;
    /**
     * Метод для заполнения графа вершинами и ребрами из базы данных.
     *
     * @return GraphView объект обертка для предоставления необходимых методов взаимодействия с графом.
     */
    public GraphView buildGraph() {
        Graph graph = new Graph();
        List<City> cities = cityRepo.findAll();
        for (City city : cities) {
            graph.addVertex(city.getId());
        }
        List<CityRoute> cityRoutes = cityRouteRepo.findAll();
        for (CityRoute cityRoute : cityRoutes) {
            long fromId = cityRoute.getId().getFromCityId();
            long toId = cityRoute.getId().getToCityId();
            long distance = cityRoute.getDistance();
            graph.addEdge(fromId, toId, distance);
        }
        return graph;
    }
}
