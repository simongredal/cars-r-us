package gredal.simon.carsrus.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.time.LocalDateTime;
import java.util.List;


@Entity
@NoArgsConstructor
@Getter @Setter
@ToString
public class Member extends BaseUser {
    private String firstName;
    private String lastName;

    private String street;
    private String city;
    private String zip;

    private boolean approved;
    private Integer ranking;

    @CreationTimestamp
    private LocalDateTime created;

    @UpdateTimestamp
    private LocalDateTime lastEdited;

    @OneToMany(mappedBy = "member")
    @ToString.Exclude
    private List<Rental> rentals;

    @OneToMany(mappedBy = "member")
    @ToString.Exclude
    private List<Reservation> reservations;

    public Member(String email, String password, String firstName, String lastName) {
        super(email, password);
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
