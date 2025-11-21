package ru.dsacademy.logisticsystem.service;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.dsacademy.logisticsystem.dto.route.RouteResponseDto;
import ru.dsacademy.logisticsystem.exception.custom.RouteNotFoundException;
import ru.dsacademy.logisticsystem.service.pathfinder.DijkstraShortestPathFinderService;
import ru.dsacademy.logisticsystem.service.pathfinder.ShortestPathFinderService;
import ru.dsacademy.logisticsystem.util.graph.Vertex;
/**
 * Сервис для расчета оптимального маршрута между двумя городами.
 * Использует предзагруженный граф из {@link GraphHolderService}
 * и алгоритм Дейкстры, реализованный в {@link DijkstraShortestPathFinderService}.
 */
@RequiredArgsConstructor
@Service
public class RouteCalculationService {
    private final GraphHolderService graphHolderService;
    private final ShortestPathFinderService pathFinderService;
    /**
     * Метод получает стартовую и конечную вершины из GraphHolderService,
     * запускает алгоритм Дейкстры от стартовой вершины и формирует объект.
     * @param idFrom id стартовой вершины (id города)
     * @param idTo id конечной вершины (id города)
     * @return DTO RouteResult объект с минимальной дистанцией и пути в виде списка
     * @throws RouteNotFoundException если путь не найден
     */
    public RouteResponseDto calculationOptionalPath(Long idFrom, Long idTo) {
        Vertex start = graphHolderService.getGraph().get(idFrom);
        Vertex end = graphHolderService.getGraph().get(idTo);
        if (start == null || end == null) {
            throw new RouteNotFoundException("Маршрут не найден", "startId", idFrom.toString(), "endId", idTo.toString());
        }
        pathFinderService.compute(start);
        return new RouteResponseDto(end.getDistance(), pathFinderService.getPath(end));
    }
}
