package gredal.simon.carsrus.security;

import gredal.simon.carsrus.entity.BaseUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<BaseUser, Long> {
    Optional<BaseUser> findByEmail(String email);

    Boolean existsByEmail(String email);
}
