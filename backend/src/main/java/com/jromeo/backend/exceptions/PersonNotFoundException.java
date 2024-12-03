package com.jromeo.backend.exceptions;

/**
 * Custom exception class for Person not found.
 *
 * @author Johan Romeo
 */
public class PersonNotFoundException extends RuntimeException {

    public PersonNotFoundException(String message) {
        super(message);
    }
}
