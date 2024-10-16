package com.jromeo.backend.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ProvisionNotFoundException.class)
    public ResponseEntity<ErrorMessage> handleProvisionNotFoundException(ProvisionNotFoundException e) {
        ErrorMessage errorMessage = new ErrorMessage(new Date(), e.getMessage());

        return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(IllegalEmailException.class)
    public ResponseEntity<ErrorMessage> handleIllegalEmailException(IllegalEmailException e) {
        ErrorMessage errorMessage = new ErrorMessage(new Date(), e.getMessage());

        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
    }
}
