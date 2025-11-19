package ru.dsacademy.logisticsystem.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.dsacademy.logisticsystem.dto.RouteResult;
import ru.dsacademy.logisticsystem.util.graph.Vertex;



/**
 * Сервис для расчета оптимального маршрута между двумя городами.
 * Использует предзагруженный граф из {@link GraphHolderService}
 * и алгоритм Дейкстры, реализованный в {@link DijkstraService}.
 * */
@RequiredArgsConstructor
@Service
public class RouteCalculationService {

    private final GraphHolderService graphHolderService;
    private final DijkstraService dijkstraService;


    /**
     * Метод получает стартовую и конечную вершины из GraphHolderService,
     * запускает алгоритм Дейкстры от стартовой вершины и формирует объект.
     * @param idFrom id стартовой вершины (id города)
     * @param idTo id конечной вершины (id города)
     * @return DTO RouteResult объект с минимальной дистанцией и пути в виде списка
     * */
    public RouteResult calculationOptionalPath(Long idFrom, Long idTo) {

        Vertex start = graphHolderService.getGraph().get(idFrom);

        Vertex end = graphHolderService.getGraph().get(idTo);

        dijkstraService.compute(start);

        return new RouteResult(end.getDistance(), dijkstraService.getPath(end));
    }

}
