package gredal.simon.carsrus.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;

import gredal.simon.carsrus.entity.Car;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@Getter @Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CarResponse {
    private Long id;

    private String brand;
    private String model;
    private Integer year;
    private Integer dailyPriceInCents;
    private Double bestDiscountPercentage;

    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss",shape = JsonFormat.Shape.STRING)
    private LocalDateTime created;

    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss",shape = JsonFormat.Shape.STRING)
    private LocalDateTime lastEdited;

    public CarResponse(Car car, Boolean includeAll) {
        this.id = car.getId();

        this.brand = car.getBrand();
        this.model = car.getModel();
        this.year = car.getYear();
        this.dailyPriceInCents = car.getDailyPriceInCents();

        if (includeAll) {
            this.bestDiscountPercentage = car.getBestDiscountPercentage();
            this.created = car.getCreated();
            this.lastEdited = car.getLastEdited();
        }
    }

    public static CarResponse of(Car entity) {
        return new CarResponse(entity, true);
    }

    public static List<CarResponse> of(List<Car> entities) {
        return entities.stream().map(CarResponse::of).toList();
    }


}
