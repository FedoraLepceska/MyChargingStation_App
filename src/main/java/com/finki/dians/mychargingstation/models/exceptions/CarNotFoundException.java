package com.finki.dians.mychargingstation.models.exceptions;

public class CarNotFoundException extends RuntimeException {

    public CarNotFoundException(){
        super("Selected car is not found.");
    }

}
