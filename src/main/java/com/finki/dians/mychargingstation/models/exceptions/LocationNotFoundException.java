package com.finki.dians.mychargingstation.models.exceptions;

public class LocationNotFoundException extends RuntimeException {

    public LocationNotFoundException() {
        super("Location is not found.");
    }
}
