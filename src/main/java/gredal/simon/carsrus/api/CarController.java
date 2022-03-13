package gredal.simon.carsrus.api;

import gredal.simon.carsrus.dto.CarRequest;
import gredal.simon.carsrus.dto.CarResponse;
import gredal.simon.carsrus.service.CarService;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@RestController
@RequestMapping("/api/cars")
@AllArgsConstructor
@CrossOrigin
public class CarController {
    private final CarService carService;

    // Should be accessible by anyone
    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<CarResponse> getCars() {
        return carService.getCars();
    }

    // Should be accessible by anyone
    @GetMapping(path = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public CarResponse getCar(@PathVariable Long id) {
        return carService.getCar(id, false);
    }

    // Should be accessible by admin
    @PostMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    @RolesAllowed("ADMIN")
    public CarResponse addCar(@RequestBody CarRequest body) {
        return carService.addCar(body);
    }

    // Should be accessible by admin
    @PutMapping(path = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public CarResponse editCar(@RequestBody CarRequest body, @PathVariable Long id) {
        return carService.editCar(body, id);
    }

    // Should be accessible by admin
    @DeleteMapping(path = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public void deleteCar(@PathVariable Long id) {
        carService.deleteCar(id);
    }
}
