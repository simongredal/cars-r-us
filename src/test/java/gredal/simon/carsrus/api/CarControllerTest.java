package gredal.simon.carsrus.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import gredal.simon.carsrus.dto.CarRequest;
import gredal.simon.carsrus.entity.Car;
import gredal.simon.carsrus.repository.CarRepository;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class CarControllerTest {
    private static Long carFordId;
    private static Long carSuzukiId;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private CarRepository carRepository;

    @BeforeAll
    static void setUp(@Autowired CarRepository carRepository) {
        carFordId = carRepository.save(new Car("Ford", "Focus", 2008, 400, 0.10)).getId();
        carSuzukiId = carRepository.save(new Car("Suzuki", "Vitara", 2011, 500_00, 0.14)).getId();
    }

    @AfterAll
    static void tearDown(@Autowired CarRepository carRepository) {
        carRepository.deleteAll();
    }

    @Test
    void getCars() {
    }

    @Test
    void getExistingCar() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/cars/" + carFordId)
                        .accept(MediaType.APPLICATION_JSON)
                ).andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(carFordId))
                .andExpect(MockMvcResultMatchers.jsonPath("$.model").value("Focus"));
    }

    @Test
    void getNotExistingCar() throws Exception {
        var getRequest = MockMvcRequestBuilders
                .get("/api/cars/" + 42069)
                .accept(MediaType.APPLICATION_JSON);

        mockMvc.perform(getRequest)
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(MockMvcResultMatchers.jsonPath("$.path").value("/api/cars/" + 42069))
                .andExpect(MockMvcResultMatchers.jsonPath("$.reason").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.exception").value("CarNotFoundException"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.status").value(404));
    }

    @Test
    void testAddCar() throws Exception {
        CarRequest carRequest = new CarRequest("VW", "Polo", 2007, 400_00, 0.10);
        var postRequest = MockMvcRequestBuilders
                .post("/api/cars")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(carRequest));

        mockMvc.perform(postRequest)
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists());

        //Verify that it actually ended in the database
        assertEquals(3, carRepository.count());
    }

    @Test
    void editCar() {
    }

    @Test
    void deleteCar() {
    }
}

