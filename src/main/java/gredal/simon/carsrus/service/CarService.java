package gredal.simon.carsrus.service;

import gredal.simon.carsrus.dto.CarRequest;
import gredal.simon.carsrus.dto.CarResponse;
import gredal.simon.carsrus.exception.CarNotFoundException;
import gredal.simon.carsrus.repository.CarRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CarService {
    private final CarRepository carRepository;

    public List<CarResponse> getCars() {
        return CarResponse.of(carRepository.findAll());
    }

    public CarResponse getCar(Long id, Boolean includeAll) {
        if (!carRepository.existsById(id)) throw new CarNotFoundException("Invalid id");

        return CarResponse.of(carRepository.getById(id));
    }

    public CarResponse addCar(CarRequest body) {
        return CarResponse.of(carRepository.save(body.toCar()));
    }

    public CarResponse editCar(CarRequest body, Long id) {
        return null;
    }

    public void deleteCar(Long id) {
        carRepository.deleteById(id);
    }
}

