package com.quiroprax.api.infra.errors.exceptions;

import org.springframework.http.HttpStatus;

public abstract class BaseHttpMappedException extends RuntimeException {
    private final HttpStatus httpStatus;
    private String uri;

    public abstract HttpStatus getHttpStatus();

    public BaseHttpMappedException(String message) {
        super(message);
        this.httpStatus = getHttpStatus();
    }

    public BaseHttpMappedException(String message, Exception exception) {
        super(message, exception);
        this.httpStatus = getHttpStatus();
    }

    public BaseHttpMappedException(String message, String uri) {
        super(message);
        this.httpStatus = getHttpStatus();
        this.uri = uri;
    }

    public String getUri() {
        return uri;
    }
}
