package gredal.simon.carsrus.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import gredal.simon.carsrus.entity.Car;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@Getter @Setter @ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CarResponse {
    private Long id;

    private String brand;
    private String model;
    private Integer year;
    private Integer dailyPriceInCents;
    private Double bestDiscountPercentage;

    private LocalDateTime created;
    private LocalDateTime lastEdited;

    public CarResponse(Car car, Boolean includeAll) {
        if (car == null) return;
        this.id = car.getId();
        this.brand = car.getBrand();
        this.model = car.getModel();
        this.year = car.getYear();
        this.dailyPriceInCents = car.getDailyPriceInCents();


        if (!includeAll) return;
        this.bestDiscountPercentage = car.getBestDiscountPercentage();
        this.created = car.getCreated();
        this.lastEdited = car.getLastEdited();
    }

    public static CarResponse of(Car entity) {
        return new CarResponse(entity, true);
    }

    public static List<CarResponse> of(List<Car> entities) {
        return entities.stream().map(CarResponse::of).toList();
    }
}
