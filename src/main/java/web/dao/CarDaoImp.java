package web.dao;

import org.springframework.stereotype.Repository;
import web.model.Car;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
public class CarDaoImp implements CarDao{
    List<Car> carList = new ArrayList<>(Arrays.asList(
            new Car("Ferrari", 1, Car.Color.Red),
            new Car("Mersedes Benz", 2, Car.Color.White),
            new Car("Lada Vesta", 3, Car.Color.Green),
            new Car("Lamborghini", 4, Car.Color.Red),
            new Car("Niva", 5, Car.Color.White)
    ));

    public List<Car> getStore() {
        return carList;
    }
}

