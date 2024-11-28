package com.jromeo.backend.exceptions;

/**
 * Exception class for Recipe not found.
 *
 * @author Johan Romeo
 */
public class RecipeNotFoundException extends RuntimeException {

    public RecipeNotFoundException(String message) {
        super(message);
    }
}
