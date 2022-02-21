package gredal.simon.carsrus.repository;

import gredal.simon.carsrus.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    List<Reservation> findByMember_Email(String email);
    List<Reservation> findByMember_Id(Long id);
    List<Reservation> findByCar_Id(Long id);
    Boolean existsByRentalDateAndCar_Id(LocalDate rentalDate, Long carId);
}
