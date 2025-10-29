package com.Majstro.psmsbackend.exception;

import org.springframework.http.HttpStatus;

public class UserServiceException extends RuntimeException {
    private final HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;

    public UserServiceException(String message, Throwable cause) {
        super(message, cause);
    }


    public HttpStatus getStatus() {
        return status;
    }
}