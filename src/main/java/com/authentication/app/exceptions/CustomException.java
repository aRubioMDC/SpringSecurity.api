package com.authentication.app.exceptions;

import org.springframework.http.HttpStatus;

/**
 *
 * @author carubio
 */
public class CustomException extends RuntimeException {
    private HttpStatus status;

    public CustomException(HttpStatus status, String message) {
        super(message);
        this.status = status;
    }

    public HttpStatus getStatus() {
        return status;
    }
}
