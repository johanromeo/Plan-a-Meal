package com.jromeo.backend.exceptions;

/**
 * Custom exception class for Provision not found.
 *
 * @author Johan Romeo
 */
public class ProvisionNotFoundException extends RuntimeException {

    public ProvisionNotFoundException(String message) {
        super(message);
    }
}
