package ru.dsacademy.logisticsystem.controller;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.dsacademy.logisticsystem.dto.truck.TruckRequestDto;
import ru.dsacademy.logisticsystem.dto.truck.TruckResponseDto;
import ru.dsacademy.logisticsystem.entity.Truck;
import ru.dsacademy.logisticsystem.service.TruckService;
/**
 * REST - контроллер для взаимодействия с сущностью {@link Truck}.
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/truck")
public class TruckController {
    private final TruckService truckService;
    /**
     * Добавляет новый грузовик.
     * @param truck объект грузовика
     * @return добавленный грузовик
     */
    @PostMapping("/add")
    public ResponseEntity<TruckResponseDto> addTruck(@Valid @RequestBody TruckRequestDto truck) {
        TruckResponseDto truckResponseDto = truckService.addTruck(truck);
        return ResponseEntity.ok(truckResponseDto);
    }
}
