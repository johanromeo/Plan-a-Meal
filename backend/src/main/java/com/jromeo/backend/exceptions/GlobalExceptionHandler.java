package com.jromeo.backend.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ProvisionNotFoundException.class)
    public ResponseEntity<ErrorMessagePayload> handleProvisionNotFoundException(ProvisionNotFoundException e) {
        ErrorMessagePayload errorMessagePayload = new ErrorMessagePayload(new Date(), e.getMessage());

        return new ResponseEntity<>(errorMessagePayload, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(IllegalEmailException.class)
    public ResponseEntity<ErrorMessagePayload> handleIllegalEmailException(IllegalEmailException e) {
        ErrorMessagePayload errorMessagePayload = new ErrorMessagePayload(new Date(), e.getMessage());

        return new ResponseEntity<>(errorMessagePayload, HttpStatus.BAD_REQUEST);
    }
}
