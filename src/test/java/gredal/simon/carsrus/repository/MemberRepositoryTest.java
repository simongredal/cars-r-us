package gredal.simon.carsrus.repository;

import gredal.simon.carsrus.entity.Member;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
public class MemberRepositoryTest {
    @Autowired
    MemberRepository repository;

    @BeforeAll
    static void setUp(@Autowired MemberRepository repository) {
        repository.saveAll(List.of(
                new Member("ironman@gmail.com", "password", "tony", "stark"),
                new Member("spiderman@gmail.com", "password", "peter", "parker"),
                new Member("black.widow@gmail.com", "password", "natasha", "romanoff"),
                new Member("hulk@gmail.com", "password", "bruce", "banner"),
                new Member("captain.america@gmail.com", "password", "steve", "rogers")
        ));
    }

    @Test
    void testCount() {
        assertEquals(5, repository.count());
    }
}
