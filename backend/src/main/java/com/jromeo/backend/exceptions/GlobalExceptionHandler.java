package com.jromeo.backend.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Date;

/**
 * The type Global exception handler.
 *
 * @author Johan Romeo
 */
@ControllerAdvice
public class GlobalExceptionHandler {

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
}
