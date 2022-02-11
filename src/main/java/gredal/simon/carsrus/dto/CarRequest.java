package gredal.simon.carsrus.dto;

import gredal.simon.carsrus.entity.Car;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter @Setter
@ToString
public class CarRequest {
    private String brand;
    private String model;
    private Integer year;
    private Integer dailyPriceInCents;
    private Double bestDiscountPercentage;

    public Car toCar() {
        Car car = new Car();
        car.setBrand(brand);
        car.setModel(model);
        car.setYear(year);
        car.setDailyPriceInCents(dailyPriceInCents);
        car.setBestDiscountPercentage(bestDiscountPercentage);
        return car;
    }
}
