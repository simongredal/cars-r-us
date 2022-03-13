package gredal.simon.carsrus.security.api;

import gredal.simon.carsrus.exception.InvalidLoginException;
import gredal.simon.carsrus.security.UserService;
import gredal.simon.carsrus.security.dto.LoginRequest;
import gredal.simon.carsrus.security.dto.LoginResponse;
import gredal.simon.carsrus.security.dto.SignupRequest;
import gredal.simon.carsrus.security.dto.SignupResponse;
import gredal.simon.carsrus.security.jwt.JwtTokenUtil;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/api/auth")
@AllArgsConstructor
public class AuthenticationController {
    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtil jwtTokenUtil;
    private final UserService userService;

    @PostMapping("login")
    public ResponseEntity<LoginResponse> login(@RequestBody @Valid LoginRequest request) {
        System.out.println(request);
        try {
            var usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword());
            Authentication authenticate = authenticationManager.authenticate(usernamePasswordAuthenticationToken);

            UserDetails user = (UserDetails) authenticate.getPrincipal();
            String token = jwtTokenUtil.generateAccessToken(user);
            List<String> rolesNames = user.getAuthorities().stream()
                    .map(GrantedAuthority::getAuthority)
                    .toList();

            return ResponseEntity.ok()
                    .header(HttpHeaders.AUTHORIZATION, token)
                    .body(new LoginResponse(user.getUsername(), token, rolesNames));

        } catch (AuthenticationException ex) {
            throw new InvalidLoginException();
        }
    }

    @PostMapping("register")
    public ResponseEntity<SignupResponse> register(@RequestBody @Valid SignupRequest request) {
        return ResponseEntity.ok(userService.createUser(request));
    }

}
