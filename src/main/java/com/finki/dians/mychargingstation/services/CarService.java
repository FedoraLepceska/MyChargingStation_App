package com.finki.dians.mychargingstation.services;

import com.finki.dians.mychargingstation.models.Car;
import com.finki.dians.mychargingstation.repositories.CarRepository;
import com.finki.dians.mychargingstation.services.serviceinterfaces.CarServiceInterface;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarService implements CarServiceInterface {

    private final CarRepository carRepository;

    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @Override
    public List<Car> listAll() {
        return carRepository.findAll();
    }

    @Override
    public Optional<Car> findById(int car_id) {
        return carRepository.findById(car_id);
    }

//    @Override
//    public List<Car> findAllByUserId(int user_id) {
//        return carRepository.findCarsByUser_id(user_id);
//    }

    @Override
    public Car create(int user_id, String car_plate, String car_model) {
        Car car = new Car(user_id, car_plate, car_model);
        return carRepository.save(car);
    }

    @Override
    public Car update(int car_id, int user_id, String car_plate, String car_model) {
        deleteById(car_id);
        Car car = new Car(user_id, car_plate, car_model);
        return carRepository.save(car);
    }

    @Override
    public void deleteById(int car_id) {
        carRepository.deleteById(car_id);
    }
}
