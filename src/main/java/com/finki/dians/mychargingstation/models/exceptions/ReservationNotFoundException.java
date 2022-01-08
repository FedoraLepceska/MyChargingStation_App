package com.finki.dians.mychargingstation.models.exceptions;

public class ReservationNotFoundException extends RuntimeException {

    public ReservationNotFoundException() {
        super("Reservation is not found.");
    }

}
