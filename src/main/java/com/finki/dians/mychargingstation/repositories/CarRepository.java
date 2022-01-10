package com.finki.dians.mychargingstation.repositories;

import com.finki.dians.mychargingstation.models.Car;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CarRepository extends JpaRepository<Car, Integer> {
//    List<Car> findCarsByUser_id(int user_id);
}
