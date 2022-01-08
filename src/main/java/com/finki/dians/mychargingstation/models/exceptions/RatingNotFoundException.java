package com.finki.dians.mychargingstation.models.exceptions;

public class RatingNotFoundException extends RuntimeException {

    public RatingNotFoundException() {
        super("Rating is not found.");
    }
}
