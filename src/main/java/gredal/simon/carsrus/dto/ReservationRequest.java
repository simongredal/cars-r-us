package gredal.simon.carsrus.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import gredal.simon.carsrus.entity.Reservation;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter @Setter @ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ReservationRequest {
    private LocalDateTime reservationDateTime;
    private LocalDateTime rentalDateTime;

    public Reservation toReservation() {
        Reservation reservation = new Reservation();
        reservation.setReservationDateTime(this.reservationDateTime);
        reservation.setRentalDateTime(this.rentalDateTime);
        return reservation;
    }
}
