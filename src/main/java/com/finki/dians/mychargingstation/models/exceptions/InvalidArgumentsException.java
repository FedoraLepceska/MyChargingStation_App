package com.finki.dians.mychargingstation.models.exceptions;

public class InvalidArgumentsException extends RuntimeException {

    public InvalidArgumentsException(){
        super("All fields must be filled.");
    }

}
