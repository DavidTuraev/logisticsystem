package ru.dsacademy.logisticsystem.entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
/**
 * Сущность, представляющая груз,
 * которая используется алгоритмом для загрузки грузовика.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "cargo", schema = "logistic_company")
@Entity
public class Cargo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Double weight;
    private Double volume;
    private Double price;
}
