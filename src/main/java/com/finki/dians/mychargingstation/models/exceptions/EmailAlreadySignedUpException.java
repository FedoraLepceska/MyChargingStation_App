package com.finki.dians.mychargingstation.models.exceptions;

public class EmailAlreadySignedUpException extends RuntimeException {

    public EmailAlreadySignedUpException(String email){
        super(String.format("User with email %s already exists", email));
    }

}
