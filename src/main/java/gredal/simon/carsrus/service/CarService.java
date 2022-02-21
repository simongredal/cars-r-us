package gredal.simon.carsrus.service;

import gredal.simon.carsrus.dto.CarRequest;
import gredal.simon.carsrus.dto.CarResponse;
import gredal.simon.carsrus.entity.Car;
import gredal.simon.carsrus.entity.Member;
import gredal.simon.carsrus.exception.CarNotFoundException;
import gredal.simon.carsrus.exception.FunctionalityNotImplementedException;
import gredal.simon.carsrus.repository.CarRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

@Service
@AllArgsConstructor
public class CarService {
    private final CarRepository carRepository;

    public List<CarResponse> getCars() {
        return CarResponse.of(carRepository.findAll());
    }

    public CarResponse getCar(Long id, Boolean includeAll) {
        if (!carRepository.existsById(id)) throw new CarNotFoundException();

        return CarResponse.of(carRepository.getById(id));
    }

    public CarResponse addCar(CarRequest body) {
        return CarResponse.of(carRepository.save(body.toCar()));
    }

    public CarResponse editCar(CarRequest body, Long id) {
        if (!carRepository.existsById(id)) throw new CarNotFoundException();

        Car car = carRepository.getById(id);
        if (body.getBrand()                  != null) car.setBrand(body.getBrand());
        if (body.getModel()                  != null) car.setModel(body.getModel());
        if (body.getYear()                   != null) car.setYear(body.getYear());
        if (body.getDailyPriceInCents()      != null) car.setDailyPriceInCents(body.getDailyPriceInCents());
        if (body.getBestDiscountPercentage() != null) car.setBestDiscountPercentage(body.getBestDiscountPercentage());

        car = carRepository.save(car);
        return CarResponse.of(car);
    }

    public void deleteCar(Long id) {
        carRepository.deleteById(id);
    }
}

