package gredal.simon.carsrus.security;


import gredal.simon.carsrus.entity.BaseUser;
import gredal.simon.carsrus.entity.Role;
import gredal.simon.carsrus.exception.EmailInvalidException;
import gredal.simon.carsrus.security.UserRepository;
import gredal.simon.carsrus.security.dto.SignupRequest;
import gredal.simon.carsrus.security.dto.SignupResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public SignupResponse createUser(SignupRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new EmailInvalidException();
        }

        BaseUser user = new BaseUser(request.getEmail(), request.getPassword());
        user.addRole(Role.USER);

        userRepository.save(user);
        List<String> roleNames = user.getRoles().stream().map(Enum::toString).toList();
        return new SignupResponse(roleNames);
    }

}
