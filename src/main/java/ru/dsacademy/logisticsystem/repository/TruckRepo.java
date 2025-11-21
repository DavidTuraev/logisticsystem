package ru.dsacademy.logisticsystem.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.dsacademy.logisticsystem.entity.Truck;
/**
 * JPA репозиторий для сущности {@link Truck}.
 */
@Repository
public interface TruckRepo extends JpaRepository<Truck, Long> {
}
