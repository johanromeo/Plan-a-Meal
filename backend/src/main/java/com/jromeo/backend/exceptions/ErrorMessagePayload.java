package com.jromeo.backend.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;


/**
 * The type Error message payload.
 *
 * @author Johan Romeo
 */
@Getter
@Setter
@AllArgsConstructor
public class ErrorMessagePayload {
    private Date timeStamp;
    private String message;
}
