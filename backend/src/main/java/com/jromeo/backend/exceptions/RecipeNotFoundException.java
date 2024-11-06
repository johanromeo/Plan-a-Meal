package com.jromeo.backend.exceptions;

/**
 * The type Recipe not found exception.
 *
 * @author Johan Romeo
 */
public class RecipeNotFoundException extends RuntimeException {
    /**
     * Instantiates a new Recipe not found exception.
     *
     * @param message the message
     * @author Johan Romeo
     */
    public RecipeNotFoundException(String message) {
        super(message);
    }
}
