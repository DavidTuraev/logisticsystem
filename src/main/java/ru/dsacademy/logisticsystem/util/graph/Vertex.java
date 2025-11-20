package ru.dsacademy.logisticsystem.util.graph;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 *Класс представления вершины для графа, который используется для вычислений алгоритма дейкстры.
 * Использует Comparable для использования в PriorityQueue в классе {@link Graph}.*/

@Getter
@Setter
@NoArgsConstructor
public class Vertex implements Comparable<Vertex> {

    private Long id;

    private Long distance = Long.MAX_VALUE;

    private List<Edge> neighbors = new ArrayList<>();

    private Vertex previous;
     /**Конструктор для инициализации вершины с помощью id вершины(города)
      * @param id - вершина(id города) */
    public Vertex(Long id) {
        this.id = id;
    }
    /**Метод для добавления соседей данной вершины
     * @param edge объект ребра в графе*/
    public void addNeighbor(Edge edge) {
        neighbors.add(edge);
    }

    @Override
    public int compareTo(Vertex o) {
        return Long.compare(distance, o.getDistance());
    }
}
