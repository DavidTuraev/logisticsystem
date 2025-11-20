package ru.dsacademy.logisticsystem.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.dsacademy.logisticsystem.dto.RouteResult;
import ru.dsacademy.logisticsystem.repository.CityRepo;
import ru.dsacademy.logisticsystem.service.RouteCalculationService;

/**
 * REST - контроллер для получения кратчайшего маршрута между двумя городами.
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/route")
public class RouteController {

    private final RouteCalculationService routeCalculationService;

    /**
     * Возвращает кратчайший путь между двумя городами.
     * Метод принимает id исходящего и конечного городов и выполняет поиск пути через {@link RouteCalculationService}
     *
     * @param fromId идентификатор исходящего города
     * @param toId   идентификатор конечного города
     * @return HTTP ответ с объектом {@link RouteResult} - расстоянием и маршрутом.
     */
    @GetMapping("/parameter")
    public ResponseEntity<?> getRoute(@RequestParam long fromId, @RequestParam long toId) {

        RouteResult result = routeCalculationService.calculationOptionalPath(fromId, toId);

        return ResponseEntity.ok(result);
    }

}
