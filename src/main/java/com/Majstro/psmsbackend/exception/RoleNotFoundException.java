package com.Majstro.psmsbackend.exception;

public class RoleNotFoundException extends RuntimeException {

    public RoleNotFoundException(String message){
        super(message);
    }
}
