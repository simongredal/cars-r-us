package gredal.simon.carsrus.api;

import gredal.simon.carsrus.dto.CarRequest;
import gredal.simon.carsrus.dto.CarResponse;
import gredal.simon.carsrus.service.CarService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cars")
@AllArgsConstructor
public class CarController {
    private final CarService carService;

    // Should be accessible by anyone
    @GetMapping
    public List<CarResponse> getCars() {
        return carService.getCars();
    }

    // Should be accessible by anyone
    @GetMapping("/{id}")
    public CarResponse getCar(@PathVariable Long id) {
        return carService.getCar(id, false);
    }

    // Should be accessible by admin
    @PostMapping
    public CarResponse addCar(@RequestBody CarRequest body) {
        return carService.addCar(body);
    }

    // Should be accessible by admin
    @PutMapping("/{id}")
    public CarResponse editCar(@RequestBody CarRequest body, @PathVariable Long id) {
        return carService.editCar(body, id);
    }

    // Should be accessible by admin
    @DeleteMapping("/{id}")
    public void deleteCar(@PathVariable Long id) {
        carService.deleteCar(id);
    }
}
