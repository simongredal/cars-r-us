package gredal.simon.carsrus.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import gredal.simon.carsrus.entity.Reservation;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@Getter @Setter @ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ReservationResponse {
    private Long id;

    private LocalDateTime reservationDateTime;
    private LocalDateTime rentalDateTime;

    private CarResponse car;
    private MemberResponse member;

    private LocalDateTime created;
    private LocalDateTime lastEdited;

    private ReservationResponse(Reservation reservation) {
        if (reservation == null) return;
        this.id = reservation.getId();
        this.reservationDateTime = reservation.getReservationDateTime();
        this.rentalDateTime = reservation.getRentalDateTime();
        this.car = CarResponse.of(reservation.getCar());
        this.member = MemberResponse.of(reservation.getMember());
        this.created = reservation.getCreated();
        this.lastEdited = reservation.getLastEdited();
    }

    public static ReservationResponse of(Reservation entity) {
        return new ReservationResponse(entity);
    }

    public static List<ReservationResponse> of(List<Reservation> entities) {
        return entities.stream().map(ReservationResponse::of).toList();
    }
}
