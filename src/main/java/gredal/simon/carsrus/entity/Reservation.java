package gredal.simon.carsrus.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@Getter @Setter
@ToString
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime reservationDateTime;
    private LocalDateTime rentalDateTime;

    @CreationTimestamp
    private LocalDateTime created;

    @UpdateTimestamp
    private LocalDateTime lastEdited;

    @ManyToOne
    private Car car;

    @ManyToOne
    private Member member;

    public Reservation(LocalDateTime reservationDateTime, LocalDateTime rentalDateTime, Member member, Car car) {
        this.reservationDateTime = reservationDateTime;
        this.rentalDateTime = rentalDateTime;
        this.member = member;
        this.car = car;
    }
}
