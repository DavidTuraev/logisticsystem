package ru.dsacademy.logisticsystem.controller;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.dsacademy.logisticsystem.dto.cargo.CargoRequestDto;
import ru.dsacademy.logisticsystem.dto.cargo.CargoResponseDto;
import ru.dsacademy.logisticsystem.entity.Cargo;
import ru.dsacademy.logisticsystem.service.CargoService;
/**
 * REST - контроллер для взаимодействия с сущностью {@link Cargo}.
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/cargo")
public class CargoController {
    private final CargoService cargoService;
    /**
     * Добавляет новый груз в систему.
     * @param cargo новый груз
     * @return сохранённый груз
     */
    @PostMapping("/add")
    public ResponseEntity<CargoResponseDto> addCargo(@Valid @RequestBody CargoRequestDto cargo) {
        CargoResponseDto responseDto = cargoService.addCargo(cargo);
        return ResponseEntity.ok(responseDto);
    }
}
