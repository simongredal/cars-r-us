package gredal.simon.carsrus.security;

import gredal.simon.carsrus.entity.BaseUser;
import gredal.simon.carsrus.security.UserDetailsImp;
import gredal.simon.carsrus.security.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserDetailsServiceImp implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final Optional<BaseUser> optionalUser = userRepository.findByEmail(username);
        return optionalUser
                .map(UserDetailsImp::new)
                .orElseThrow(() -> new BadCredentialsException(""));
    }

}
