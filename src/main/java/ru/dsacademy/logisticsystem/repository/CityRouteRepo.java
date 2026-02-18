package ru.dsacademy.logisticsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.dsacademy.logisticsystem.entity.CityRoute;
import ru.dsacademy.logisticsystem.entity.CityRouteId;
/**
 * JPA репозиторий для сущности {@link CityRoute}.
 */
@Repository
public interface CityRouteRepo extends JpaRepository<CityRoute, CityRouteId> {
}
