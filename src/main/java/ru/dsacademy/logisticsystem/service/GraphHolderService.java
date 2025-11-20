package ru.dsacademy.logisticsystem.service;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import ru.dsacademy.logisticsystem.util.graph.GraphBuilder;
import ru.dsacademy.logisticsystem.util.graph.GraphView;
/**
 * Данный сервис является хранением для поля Graph, а также для его инициализации.
 */
@Getter
@RequiredArgsConstructor
@Service
public class GraphHolderService {
    @Getter(AccessLevel.NONE)
    private final GraphBuilder buildGraph;
    private GraphView graph;
    /**
     * Метод создает объект Graph и сохраняет его в поле graph.
     */
    @EventListener(ApplicationReadyEvent.class)
    private void init() {
        this.graph = buildGraph.buildGraph();
    }
    /**
     * Метод перестраивает Graph полностью и сохраняет в поле graph.
     */
    public void reloadGraph() {
        this.graph = buildGraph.buildGraph();
    }
}
