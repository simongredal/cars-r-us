package gredal.simon.carsrus.configuration;

import gredal.simon.carsrus.entity.Car;
import gredal.simon.carsrus.entity.Member;
import gredal.simon.carsrus.entity.Reservation;
import gredal.simon.carsrus.entity.Role;
import gredal.simon.carsrus.repository.CarRepository;
import gredal.simon.carsrus.repository.MemberRepository;
import gredal.simon.carsrus.repository.RentalRepository;
import gredal.simon.carsrus.repository.ReservationRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.time.LocalDateTime;
import java.util.List;

@Configuration
@Profile("!test")
@AllArgsConstructor
public class MakeTestData implements ApplicationRunner {
    private final MemberRepository memberRepository;
    private final CarRepository carRepository;
    private final ReservationRepository reservationRepository;
    private final RentalRepository rentalRepository;

    public void makeMembers() {
        Member member1 = new Member("member1@mail.com", "password", "holger", "hansen");
        member1.addRole(Role.USER);

        Member member2 = new Member("member2@mail.com", "password", "jane", "jensen");
        member1.addRole(Role.USER);

        Member member3 = new Member("member3@mail.com", "password", "frida", "frederiksen");
        member1.addRole(Role.USER);

        memberRepository.save(member1);
        memberRepository.save(member2);
        memberRepository.save(member3);
    }

    public void makeCars() {
        List<Car> cars = List.of(
                new Car("Mercedes Benz", "E250 Coupé", 2018, 1_800_00, 0.15),
                new Car("Mercedes Benz", "Sprinter 316", 2018, 1_100_00, 0.15),
                new Car("Skoda", "Fabia 1,5 TSI", 2020, 900_00, 0.05),
                new Car("Skoda", "Superb 2,0 TSI 4x4", 2019, 1_400_00, 0.10),
                new Car("Peugeot", "208", 2016, 800_00, 0.05),
                new Car("Peugeot", "308 SW", 2015, 1_100_00, 0.05)
        );

        carRepository.saveAll(cars);
    }

    public void makeReservations() {
        List<Reservation> reservations = List.of(
                new Reservation(LocalDateTime.now(), LocalDateTime.now().plusWeeks(2L), memberRepository.getById(1L), carRepository.getById(1L)),
                new Reservation(LocalDateTime.now(), LocalDateTime.now().plusWeeks(1L), memberRepository.getById(2L), carRepository.getById(1L)),
                new Reservation(LocalDateTime.now(), LocalDateTime.now().plusWeeks(1L), memberRepository.getById(3L), carRepository.getById(3L)),
                new Reservation(LocalDateTime.now(), LocalDateTime.now().plusDays(3L), memberRepository.getById(2L), carRepository.getById(4L)),
                new Reservation(LocalDateTime.now(), LocalDateTime.now().plusDays(2L), memberRepository.getById(3L), carRepository.getById(6L))
        );

        reservationRepository.saveAll(reservations);
    }

    public void makeRentals() {
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        memberRepository.deleteAll();
        makeMembers();

        carRepository.deleteAll();
        makeCars();

        reservationRepository.deleteAll();
        makeReservations();

        rentalRepository.deleteAll();
        makeRentals();
    }
}
