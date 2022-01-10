package com.finki.dians.mychargingstation.models;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "car")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int car_id;
    private int user_id;
    private String car_plate;
    private String car_model;

    public Car() {
    }

    public Car(int user_id, String car_plate, String car_model) {
        this.user_id = user_id;
        this.car_plate = car_plate;
        this.car_model = car_model;
    }

    @Override
    public String toString() {
        return car_model + " " + car_plate;
    }
}
