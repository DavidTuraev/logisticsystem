package ru.dsacademy.logisticsystem.controller;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.dsacademy.logisticsystem.dto.loading.LoadingRequestDto;
import ru.dsacademy.logisticsystem.dto.loading.LoadingResponseDto;
import ru.dsacademy.logisticsystem.service.LoadingService;
/**
 * REST - контроллер для загрузки грузовика грузом.
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/load-truck")
public class LoadTruckController {
    private final LoadingService loadingService;
    /**
     * Возвращает оптимально заполненный грузовик.
     * @param loadingRequestDto dto которое передает параметры для заполнения грузовика
     * @return dto в котором посчитана оптимальная загрузка грузовика
     */
    @PostMapping()
    public ResponseEntity<LoadingResponseDto> loadTruck(@Valid @RequestBody LoadingRequestDto loadingRequestDto) {
        LoadingResponseDto loadingResponseDto = loadingService.load(loadingRequestDto);
        return ResponseEntity.ok(loadingResponseDto);
    }
}
