package ru.dsacademy.logisticsystem.util.graph;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


/**
 * Класс представления ребра в графе для алгоритма Дейкстры.*/

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Edge {

   private Vertex to;

   private Long weight;

}
