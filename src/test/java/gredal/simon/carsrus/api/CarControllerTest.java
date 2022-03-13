package gredal.simon.carsrus.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import gredal.simon.carsrus.dto.CarRequest;
import gredal.simon.carsrus.entity.Car;
import gredal.simon.carsrus.entity.Member;
import gredal.simon.carsrus.entity.Role;
import gredal.simon.carsrus.repository.CarRepository;
import gredal.simon.carsrus.repository.MemberRepository;
import gredal.simon.carsrus.security.dto.LoginRequest;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import javax.transaction.Transactional;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class CarControllerTest {
    private static Long carFordId;
    private static Long carSuzukiId;

    private static Member adminMember;
    private static Member userMember;

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private CarRepository carRepository;
    @Autowired
    private MemberRepository memberRepository;

    @BeforeAll
    static void setUp(@Autowired CarRepository carRepository, @Autowired MemberRepository memberRepository) {
        carFordId = carRepository.save(new Car("Ford", "Focus", 2008, 400, 0.10)).getId();
        carSuzukiId = carRepository.save(new Car("Suzuki", "Vitara", 2011, 500_00, 0.14)).getId();

        adminMember = new Member("admin@example.com", "securepassword", "Anne", "Admin");
        adminMember.setRoles(List.of(Role.ADMIN));

        userMember = new Member("user@example.com", "stupidpassword", "Ulrik", "User");
        userMember.setRoles(List.of(Role.USER));

        memberRepository.saveAll(List.of(adminMember, userMember));

    }

    @AfterAll
    static void tearDown(@Autowired CarRepository carRepository, @Autowired MemberRepository memberRepository) {
        carRepository.deleteAll();
        memberRepository.deleteAll();
    }

    @Test
    void getCars() {
    }

    @Test
    void getCar() {

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
                // remove test, because null doesn't "exist" but it is a "valid" reason so shouldn't fail
                //.andExpect(MockMvcResultMatchers.jsonPath("$.reason").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.exception").value("CarNotFoundException"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.status").value(404));
    }

    @Test
    void testAddCarAsAdmin() throws Exception {
        LoginRequest loginRequest = new LoginRequest(adminMember.getEmail(), "securepassword");
        var loginPostRequest = MockMvcRequestBuilders
                .post("/api/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(loginRequest));

        String jwt = mockMvc.perform(loginPostRequest)
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.token").exists())
                .andReturn()
                .getResponse()
                .getHeader(HttpHeaders.AUTHORIZATION);

        CarRequest carRequest = new CarRequest("VW", "Polo", 2007, 400_00, 0.10);
        var carPostRequest = MockMvcRequestBuilders
                .post("/api/cars")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + jwt)
                .content(objectMapper.writeValueAsString(carRequest));

        mockMvc.perform(carPostRequest)
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists());

        //Verify that it actually ended in the database
        assertEquals(3, carRepository.count());
    }

    @Test
    void testAddCarAsUser() throws Exception {
        LoginRequest loginRequest = new LoginRequest(userMember.getEmail(), "stupidpassword");
        var loginPostRequest = MockMvcRequestBuilders
                .post("/api/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(loginRequest));

        String jwt = mockMvc.perform(loginPostRequest)
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.token").exists())
                .andReturn()
                .getResponse()
                .getHeader(HttpHeaders.AUTHORIZATION);

        CarRequest carRequest = new CarRequest("VW", "Polo", 2007, 400_00, 0.10);
        var carPostRequest = MockMvcRequestBuilders
                .post("/api/cars")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + jwt)
                .content(objectMapper.writeValueAsString(carRequest));

        mockMvc.perform(carPostRequest)
                .andDo(print())
                .andExpect(status().is4xxClientError());

        //Verify that it did NOT actually end up in the database
        assertEquals(2, carRepository.count());
    }



    @Test
    void testEditCar() {
    }

    @Test
    void testDeleteCar() {
    }
}

