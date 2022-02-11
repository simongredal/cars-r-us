package gredal.simon.carsrus.service;

import gredal.simon.carsrus.dto.CarRequest;
import gredal.simon.carsrus.dto.CarResponse;
import gredal.simon.carsrus.entity.Car;
import gredal.simon.carsrus.repository.CarRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class CarServiceMockitoTest {
    @Mock
    CarRepository carRepository;

    @Autowired
    CarService carService;

    @BeforeEach
    void setUp() {
        carService = new CarService(carRepository);
    }

    @Test
    void testGetCars() {
        List<Car> carEntities = List.of(
                new Car("Volvo", "V70", 2010, 800_00, 0.10),
                new Car("Volvo", "V40", 2012, 850_00, 0.10)
        );

        Mockito.when(carRepository.findAll()).thenReturn(carEntities);

        List<CarResponse> carResponses = carService.getCars();
        assertEquals(2, carResponses.size());
    }

    @Test
    void testGetCar() {
        Car carEntity = new Car("Volvo", "V70", 2010, 800_00, 0.10);
        carEntity.setId(1000L);

        Mockito.when(carRepository.existsById(1000L)).thenReturn(true);
        Mockito.when(carRepository.getById(1000L)).thenReturn(carEntity);

        CarResponse carResponse = carService.getCar(1000L, true);
        assertEquals("Volvo", carResponse.getBrand());
    }

    @Test
    void testAddCar() {
        Car carEntity = new Car("Volvo", "V70", 2010, 800_00, 0.10);
        carEntity.setId(1000L);

        Mockito.when(carRepository.save(any(Car.class))).thenReturn(carEntity);

        CarRequest carRequest = new CarRequest(carEntity.getBrand(), carEntity.getModel(), carEntity.getYear(), carEntity.getDailyPriceInCents(), carEntity.getBestDiscountPercentage());
        CarResponse carResponse = carService.addCar(carRequest);

        assertEquals(1000, carResponse.getId());
    }

    @Test
    void testEditCar() {
    }

    @Test
    void testDeleteCar() {
    }
}
