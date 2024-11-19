package com.jromeo.backend.exceptions;

/**
 * The type Person not found exception.
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
