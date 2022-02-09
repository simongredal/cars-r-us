package gredal.simon.carsrus.security;

import gredal.simon.carsrus.entity.Role;

import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

public interface UserWithPassword {
    int USER_NAME_MIN_SIZE = 2;
    int USER_NAME_MAX_SIZE = 50;

    int EMAIL_MAX_SIZE = 250;

    int PASSWORD_MIN_SIZE = 12;
    PasswordEncoder passwordEncoder = new Argon2PasswordEncoder(16, 32, 1, 1<<14, 4);

    String getEmail();
    void setEmail(String email);

    String getPassword();
    void setPassword(String password);

    List<Role> getRoles();

    void addRole(Role role);

    static PasswordEncoder getPasswordEncoder(){
        return passwordEncoder;
    }

    boolean isEnabled();
}
