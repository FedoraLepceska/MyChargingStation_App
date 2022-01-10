package com.finki.dians.mychargingstation.services.serviceinterfaces;

import com.finki.dians.mychargingstation.models.Car;
import com.finki.dians.mychargingstation.models.Location;
import com.finki.dians.mychargingstation.models.enums.ChargerType;

import java.util.List;
import java.util.Optional;

public interface CarServiceInterface {
    List<Car> listAll();

    Optional<Car> findById(int car_id);

//    List<Car> findAllByUserId(int user_id);

    Car create(int user_id, String car_plate, String car_model);

    Car update(int car_id, int user_id, String car_plate, String car_model);

    void deleteById(int car_id);

}
