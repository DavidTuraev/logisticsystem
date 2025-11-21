package ru.dsacademy.logisticsystem.entity;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import java.util.Objects;
/**
 * Составной ключ для сущности CityRoute
 * Ключ состоит из:
 * - fromCityId - id города отправления
 * - toCityId - id города назначения.
 * Используется для идентификации маршрута между двумя городами.
 */
@Getter
@Embeddable
@AllArgsConstructor
@NoArgsConstructor
public class CityRouteId implements Serializable {
    @Column(name = "id_from_city")
    private Long fromCityId;
    @Column(name = "id_to_city")
    private Long toCityId;
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof CityRouteId that)) {
            return false;
        }
        return Objects.equals(fromCityId, that.fromCityId) && Objects
                .equals(toCityId, that.toCityId);
    }
    @Override
    public int hashCode() {
        return Objects.hash(fromCityId, toCityId);
    }
}
