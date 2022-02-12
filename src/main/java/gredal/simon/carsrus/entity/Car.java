package gredal.simon.carsrus.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter @Setter
@ToString
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String brand;
    private String model;
    private Integer year;
    private Integer dailyPriceInCents;
    private Double bestDiscountPercentage;

    @CreationTimestamp
    private LocalDateTime created;

    @UpdateTimestamp
    private LocalDateTime lastEdited;

    @OneToMany(mappedBy = "car")
    @ToString.Exclude
    private List<Reservation> reservation;

    @OneToMany(mappedBy = "car")
    @ToString.Exclude
    private List<Rental> rentals;

    public Car(String brand, String model, Integer year, Integer dailyPriceInCents, Double bestDiscountPercentage) {
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.dailyPriceInCents = dailyPriceInCents;
        this.bestDiscountPercentage = bestDiscountPercentage;
    }
}
