package com.jromeo.backend.exceptions;

/**
 * Custom-made exception class for handling {@code com.jromeo.backend.person}-related issues.
 *
 * @author Johan Romeo
 */
public class PersonNotFoundException extends RuntimeException {
    /**
     * Instantiates a new Person not found exception.
     *
     * @param message the message
     * @author Johan Romeo
     */
    public PersonNotFoundException(String message) {
        super(message);
    }
}
