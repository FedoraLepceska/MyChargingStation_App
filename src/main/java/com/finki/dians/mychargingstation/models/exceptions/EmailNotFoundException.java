package com.finki.dians.mychargingstation.models.exceptions;

public class EmailNotFoundException extends RuntimeException {

    public EmailNotFoundException(){
        super("Incorrect email.");
    }

}
