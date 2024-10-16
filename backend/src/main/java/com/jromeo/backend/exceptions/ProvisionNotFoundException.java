package com.jromeo.backend.exceptions;

public class ProvisionNotFoundException extends RuntimeException {

    public ProvisionNotFoundException(String message) {
        super(message);
    }
}
