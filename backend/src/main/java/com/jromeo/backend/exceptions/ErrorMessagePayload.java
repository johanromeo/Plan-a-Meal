package com.jromeo.backend.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;


/**
 * Class for creating a custom error message payload with date and a message.
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
