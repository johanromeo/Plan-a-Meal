package com.jromeo.backend.exceptions;

/**
 * The type Provision not found exception.
 *
 * @author Johan Romeo
 */
public class ProvisionNotFoundException extends RuntimeException {

    /**
     * Instantiates a new Provision not found exception.
     *
     * @param message the message
     * @author Johan Romeo
     */
    public ProvisionNotFoundException(String message) {
        super(message);
    }
}
