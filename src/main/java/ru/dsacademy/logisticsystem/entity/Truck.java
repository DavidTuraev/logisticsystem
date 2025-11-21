package ru.dsacademy.logisticsystem.entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
/**
 * Сущность грузовика,
 * которая используется для загрузки грузов с помощью алгоритма.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "truck", schema = "logistic_company")
@Entity
public class Truck {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "max_weight")
    private Long maxWeight;
    @Column(name = "max_volume")
    private Long maxVolume;
}
