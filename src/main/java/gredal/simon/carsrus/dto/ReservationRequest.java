package gredal.simon.carsrus.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import gredal.simon.carsrus.entity.Reservation;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@NoArgsConstructor
@Getter @Setter @ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ReservationRequest {
    private LocalDate reservationDate;
    private LocalDate rentalDate;

    public Reservation toReservation() {
        Reservation reservation = new Reservation();
        reservation.setReservationDate(this.reservationDate);
        reservation.setRentalDate(this.rentalDate);
        return reservation;
    }
}
