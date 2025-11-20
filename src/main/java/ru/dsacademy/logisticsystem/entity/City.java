package ru.dsacademy.logisticsystem.entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
/**
 * Сущность города в системе логистики.
 * Используется как элемент графа дорог
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "city", schema = "logistic_company")
@Entity
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @Column(name = "name", unique = true)
    private String name;
}
