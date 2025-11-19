package ru.dsacademy.logisticsystem.util.graph;

import java.util.List;


/**
 * Интерфейс доступа к графу, обеспечивающий доступ к вершинам.*/
public interface GraphView {

    /**
     * Возвращает вершину по id.
     * @param id - идентификатор вершины
     * @return объект Vertex или null если вершина отсутствует*/
    Vertex get(long id);

    /**
     * Возвращает список всех вершин графа
     * @return список всех вершин графа*/
    List<Vertex> getAllVertexes();

}
