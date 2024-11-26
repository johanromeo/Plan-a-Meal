package com.jromeo.backend.exceptions;

/**
 * Custom-made exception class for handling wrong email provided by users of the application.
 *
 * @author Johan Romeo
 */
public class IllegalEmailException extends RuntimeException {

    public IllegalEmailException(String message) {
        super(message);
    }
}
