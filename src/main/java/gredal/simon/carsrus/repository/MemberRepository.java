package gredal.simon.carsrus.repository;

import gredal.simon.carsrus.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    Member findByEmail(String email);
    Boolean existsByEmail(String email);
}
