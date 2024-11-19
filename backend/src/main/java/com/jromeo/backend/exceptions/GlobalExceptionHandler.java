package com.jromeo.backend.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.View;

import java.util.Date;

/**
 * The type Global exception handler.
 *
 * @author Johan Romeo
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    private final View error;

    /**
     * Instantiates a new Global exception handler.
     *
     * @param error the error
     * @author Johan Romeo
     */
    public GlobalExceptionHandler(View error) {
        this.error = error;
    }

    /**
     * Handle provision not found exception response entity.
     *
     * @param e the e
     * @return the response entity
     * @author Johan Romeo
     */
    @ExceptionHandler(ProvisionNotFoundException.class)
    public ResponseEntity<ErrorMessagePayload> handleProvisionNotFoundException(ProvisionNotFoundException e) {
        ErrorMessagePayload errorMessagePayload = new ErrorMessagePayload(new Date(), e.getMessage());

        return new ResponseEntity<>(errorMessagePayload, HttpStatus.NOT_FOUND);
    }

    /**
     * Handle illegal email exception response entity.
     *
     * @param e the e
     * @return the response entity
     * @author Johan Romeo
     */
    @ExceptionHandler(IllegalEmailException.class)
    public ResponseEntity<ErrorMessagePayload> handleIllegalEmailException(IllegalEmailException e) {
        ErrorMessagePayload errorMessagePayload = new ErrorMessagePayload(new Date(), e.getMessage());

        return new ResponseEntity<>(errorMessagePayload, HttpStatus.BAD_REQUEST);
    }

    /**
     * Handle person not found exception response entity.
     *
     * @param e the e
     * @return the response entity
     * @author Johan Romeo
     */
    @ExceptionHandler(PersonNotFoundException.class)
    public ResponseEntity<ErrorMessagePayload> handlePersonNotFoundException(PersonNotFoundException e) {
        ErrorMessagePayload errorMessagePayload = new ErrorMessagePayload(new Date(), e.getMessage());

        return new ResponseEntity<>(errorMessagePayload, HttpStatus.NOT_FOUND);
    }


    /**
     * Handle recipe not found exception response entity.
     *
     * @param e the e
     * @return the response entity
     * @author Johan Romeo
     */
    @ExceptionHandler(RecipeNotFoundException.class)
    public ResponseEntity<ErrorMessagePayload> handleRecipeNotFoundException(RecipeNotFoundException e) {
        ErrorMessagePayload errorMessagePayload = new ErrorMessagePayload(new Date(), e.getMessage());

        return new ResponseEntity<>(errorMessagePayload, HttpStatus.NOT_FOUND);
    }
}
