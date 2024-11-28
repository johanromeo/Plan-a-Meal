package com.jromeo.backend.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Date;

/**
 * Class handling all custom-made exceptions.
 *
 * @author Johan Romeo
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Handles {@link ProvisionNotFoundException}.
     *
     * @param e the custom error message.
     * @return the response entity with an error message and Http status 404 - Not Found.
     */
    @ExceptionHandler(ProvisionNotFoundException.class)
    public ResponseEntity<ErrorMessagePayload> handleProvisionNotFoundException(ProvisionNotFoundException e) {
        ErrorMessagePayload errorMessagePayload = new ErrorMessagePayload(new Date(), e.getMessage());

        return new ResponseEntity<>(errorMessagePayload, HttpStatus.NOT_FOUND);
    }

    /**
     * Handles {@link IllegalEmailException}.
     *
     * @param e the custom error message.
     * @return the response entity with an error message and Http status 400 - Bad Request.
     */
    @ExceptionHandler(IllegalEmailException.class)
    public ResponseEntity<ErrorMessagePayload> handleIllegalEmailException(IllegalEmailException e) {
        ErrorMessagePayload errorMessagePayload = new ErrorMessagePayload(new Date(), e.getMessage());

        return new ResponseEntity<>(errorMessagePayload, HttpStatus.BAD_REQUEST);
    }

    /**
     * Handles {@link PersonNotFoundException}.
     *
     * @param e the custom error message.
     * @return the response entity with an error message and Http status 404 - Not Found.
     */
    @ExceptionHandler(PersonNotFoundException.class)
    public ResponseEntity<ErrorMessagePayload> handlePersonNotFoundException(PersonNotFoundException e) {
        ErrorMessagePayload errorMessagePayload = new ErrorMessagePayload(new Date(), e.getMessage());

        return new ResponseEntity<>(errorMessagePayload, HttpStatus.NOT_FOUND);
    }

    /**
     * Handles {@link RecipeNotFoundException}.
     *
     * @param e the custom error message.
     * @return the response entity with an error message and Http status 404 - Not Found.
     */
    @ExceptionHandler(RecipeNotFoundException.class)
    public ResponseEntity<ErrorMessagePayload> handleRecipeNotFoundException(RecipeNotFoundException e) {
        ErrorMessagePayload errorMessagePayload = new ErrorMessagePayload(new Date(), e.getMessage());

        return new ResponseEntity<>(errorMessagePayload, HttpStatus.NOT_FOUND);
    }
}
