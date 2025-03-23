package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web.dao.CarDao;
import web.model.Car;

import java.util.List;

@Service
public class CarServiceImp implements CarService {

    private CarDao carDao;

    @Autowired
    public CarServiceImp(CarDao carDao) {
        this.carDao = carDao;
    }

    @Override
    public List<Car> getCar(List<Car> cars, int count) {
        return (count >= 5) ? cars : cars.subList(0, count);
    }

    @Override
    public List<Car> getCar(int count) {
        return (count >= 5) ? carDao. getStore() : carDao. getStore().stream().limit(count).toList();
    }

}
