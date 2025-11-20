package ru.dsacademy.logisticsystem.entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
/**
 * Используется как сущность маршрутов дорог.
 * Используется как элемент графа дорог, для получения веса и направления ребер графа
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "city_routes", schema = "logistic_company")
@Entity
public class CityRoute {
    @EmbeddedId
    private CityRouteId id;
    @ManyToOne
    @MapsId("fromCityId")
    @JoinColumn(name = "id_from_city")
    private City fromCity;
    @ManyToOne()
    @MapsId("toCityId")
    @JoinColumn(name = "id_to_city")
    private City toCity;
    @Column(name = "distance")
    private Long distance;
}
