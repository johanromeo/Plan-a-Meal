package com.jromeo.backend.exceptions;

public class IllegalEmailException extends RuntimeException {

    public IllegalEmailException(String message) {
        super(message);
    }
}
