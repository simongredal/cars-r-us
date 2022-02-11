package gredal.simon.carsrus.repository;

import gredal.simon.carsrus.entity.Car;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
class CarRepositoryTest {
    @Autowired
    CarRepository repository;

    @BeforeAll
    static void setUp(@Autowired CarRepository repository) {
        repository.saveAll(List.of(
                new Car("Mercedes Benz", "E250 Coupé", 2018, 1_800_00, 0.15),
                new Car("Mercedes Benz", "Sprinter 316", 2018, 1_100_00, 0.15),
                new Car("Skoda", "Fabia 1,5 TSI", 2020, 900_00, 0.05),
                new Car("Skoda", "Superb 2,0 TSI 4x4", 2019, 1_400_00, 0.10),
                new Car("Peugeot", "208", 2016, 800_00, 0.05),
                new Car("Peugeot", "308 SW", 2015, 1_100_00, 0.05)
        ));
    }

    @Test
    void testCount() {
        assertEquals(6, repository.count());
    }
}
