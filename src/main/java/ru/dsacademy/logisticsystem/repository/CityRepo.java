package ru.dsacademy.logisticsystem.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.dsacademy.logisticsystem.entity.City;


/** JPA репозиторий для сущности City.*/

@Repository
public interface CityRepo extends JpaRepository<City, Long> {




}
