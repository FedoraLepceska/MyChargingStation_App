package com.finki.dians.mychargingstation.models.exceptions;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException() {
        super("User is not found.");
    }

}
