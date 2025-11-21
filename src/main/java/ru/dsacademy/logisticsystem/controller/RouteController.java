package ru.dsacademy.logisticsystem.controller;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.dsacademy.logisticsystem.dto.route.RouteResponseDto;
import ru.dsacademy.logisticsystem.service.RouteCalculationService;
/**
 * REST - контроллер для получения кратчайшего маршрута между двумя городами.
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/route")
public class RouteController {
    private final RouteCalculationService routeCalculationService;
    /**
     * Возвращает кратчайший путь между двумя городами.
     * Метод принимает id исходящего и конечного городов и выполняет поиск пути через {@link RouteCalculationService}
     * @param idFrom идентификатор исходящего города
     * @param idTo идентификатор конечного города
     * @return HTTP ответ с объектом {@link RouteResponseDto} - расстоянием и маршрутом.
     */
    @GetMapping("/parameter")
    public ResponseEntity<RouteResponseDto> getRoute(@Positive @NotNull @RequestParam Long idFrom,
                                                     @Positive @NotNull @RequestParam Long idTo) {
        RouteResponseDto result = routeCalculationService.calculationOptionalPath(idFrom, idTo);
        return ResponseEntity.ok(result);
    }
}
