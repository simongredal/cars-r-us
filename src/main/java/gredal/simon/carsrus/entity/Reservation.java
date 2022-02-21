package gredal.simon.carsrus.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@Getter @Setter @ToString
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate reservationDate;
    private LocalDate rentalDate;

    @CreationTimestamp
    private LocalDateTime created;

    @UpdateTimestamp
    private LocalDateTime lastEdited;

    @ManyToOne
    private Car car;

    @ManyToOne
    private Member member;

    public Reservation(LocalDate reservationDateTime, LocalDate rentalDate, Member member, Car car) {
        this.reservationDate = reservationDateTime;
        this.rentalDate = rentalDate;
        this.member = member;
        this.car = car;
    }
}
