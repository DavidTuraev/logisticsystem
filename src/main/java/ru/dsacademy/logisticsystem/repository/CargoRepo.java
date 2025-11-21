package ru.dsacademy.logisticsystem.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.dsacademy.logisticsystem.entity.Cargo;
/**
 * JPA репозиторий для сущности {@link Cargo}.
 */
public interface CargoRepo extends JpaRepository<Cargo, Long> {
}
