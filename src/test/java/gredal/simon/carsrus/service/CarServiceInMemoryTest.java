package gredal.simon.carsrus.service;

import gredal.simon.carsrus.dto.CarRequest;
import gredal.simon.carsrus.dto.CarResponse;
import gredal.simon.carsrus.entity.Car;
import gredal.simon.carsrus.repository.CarRepository;
import org.aspectj.lang.annotation.After;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@DataJpaTest
class CarServiceInMemoryTest {
    private static CarService carService;

    @BeforeAll
    static void setUp(@Autowired CarRepository carRepository) {
        carService = new CarService(carRepository);

        List<Car> cars = List.of(
                new Car("Volvo", "V70", 2010,800_00,0.10),
                new Car("Volvo", "V40", 2015,850_00,0.10),
                new Car("VW", "Polo", 2016,750,0.05),
                new Car("VW", "Golf Mk. IV", 2004,600,0.05)
        );

        carRepository.saveAll(cars);
    }

    @AfterAll
    static void tearDown(@Autowired CarRepository carRepository) {
        carRepository.deleteAll();
    }

    @Test
    void getCars() {
        List<CarResponse> carResponses = carService.getCars();

        assertEquals(4, carResponses.size());

        // this seems dumb, java has a type system strong enough to not do this
        assertInstanceOf(CarResponse.class, carResponses.get(0));

        assertThat(carResponses, containsInAnyOrder(
                hasProperty("model", is("V70")),
                hasProperty("model", is("V40")),
                hasProperty("model", is("Polo")),
                hasProperty("model", is("Golf Mk. IV"))
        ));
    }

    @Test
    void getCar() {
    }

    @Test
    void addCar() {
    }

    @Test
    void editCar() {
    }

    @Test
    void deleteCar() {
    }
}
