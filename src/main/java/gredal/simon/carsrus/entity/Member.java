package gredal.simon.carsrus.entity;

import lombok.*;
import org.hibernate.annotations.*;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Entity
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Member extends BaseUser {
    private String firstName;
    private String lastName;

    private String street;
    private String city;
    private String zip;

    private Boolean approved;
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
