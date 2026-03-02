package com.ltc.pagination.exception;

public class AppointmentAlreadyExistsException extends RuntimeException {

    public AppointmentAlreadyExistsException(String message) {
        super(message);
    }
}