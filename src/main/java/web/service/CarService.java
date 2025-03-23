package web.service;


import web.model.Car;

import java.util.List;

public interface CarService {
    List<Car> getCar(List<Car> cars, int count) ;
    List<Car> getCar(int count) ;

}
