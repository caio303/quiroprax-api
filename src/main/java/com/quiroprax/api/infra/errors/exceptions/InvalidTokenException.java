package com.quiroprax.api.infra.errors.exceptions;

import org.springframework.http.HttpStatus;

public class InvalidTokenException extends BaseHttpMappedException {

    private static final String message = "Token expirado ou invalido";

    @Override
    public HttpStatus getHttpStatus() {
        return HttpStatus.UNAUTHORIZED;
    }

    public InvalidTokenException(String message, Exception exception) {
        super(message, exception);
    }

    public InvalidTokenException(Exception exception) {
        super(message, exception);
    }

    public InvalidTokenException(String message) {
        super(message);
    }

    public InvalidTokenException() {
        super(message);
    }
}
