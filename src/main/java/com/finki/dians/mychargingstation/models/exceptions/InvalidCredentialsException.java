package com.finki.dians.mychargingstation.models.exceptions;

public class InvalidCredentialsException extends RuntimeException {

    public InvalidCredentialsException(){
        super("Email or password are incorrect.");
    }

}
