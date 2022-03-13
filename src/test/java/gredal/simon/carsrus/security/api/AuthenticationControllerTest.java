package gredal.simon.carsrus.security.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import gredal.simon.carsrus.entity.Car;
import gredal.simon.carsrus.entity.Member;
import gredal.simon.carsrus.entity.Role;
import gredal.simon.carsrus.repository.CarRepository;
import gredal.simon.carsrus.repository.MemberRepository;
import gredal.simon.carsrus.security.dto.LoginRequest;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import javax.transaction.Transactional;

import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class AuthenticationControllerTest {
    private static Member adminMember;
    private static Member userMember;

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @BeforeAll
    static void setUp(@Autowired MemberRepository memberRepository) {
        adminMember = new Member("admin@example.com", "securepassword", "Anne", "Admin");
        adminMember.setRoles(List.of(Role.ADMIN));

        userMember = new Member("user@example.com", "stupidpassword", "Ulrik", "User");
        userMember.setRoles(List.of(Role.USER));

        memberRepository.saveAll(List.of(adminMember, userMember));
    }

    @AfterAll
    static void tearDown(@Autowired MemberRepository memberRepository) {
        memberRepository.deleteAll();
    }


    @Test
    void testInvalidLogin() throws Exception {
        LoginRequest loginRequest = new LoginRequest(userMember.getEmail(), "wrong");
        var loginPostRequest = MockMvcRequestBuilders
                .post("/api/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(loginRequest));

        mockMvc.perform(loginPostRequest)
                .andDo(print())
                .andExpect(status().isNotAcceptable())
                .andExpect(jsonPath("$.exception").value("InvalidLoginException"));
    }
}
