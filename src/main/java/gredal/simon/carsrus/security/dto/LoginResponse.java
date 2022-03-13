package gredal.simon.carsrus.security.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class LoginResponse {
    private String email;
    private String token;
    private List<String> roles;

    public LoginResponse(String email, String token, List<String> roles) {
        this.email = email;
        this.token = token;
        this.roles = roles;
    }
}
