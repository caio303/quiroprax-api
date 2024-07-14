package com.quiroprax.api.infra.errors.exceptions;

import org.springframework.http.HttpStatus;

public class EntityAlreadyExistsException extends BaseHttpMappedException {

    @Override
    public HttpStatus getHttpStatus() {
        return HttpStatus.CONFLICT;
    }

    public <T> EntityAlreadyExistsException(Class<T> entityClass, String fieldName, Object value) {
        super(entityClass.getSimpleName() + " com '" + fieldName + "' igual a '" +value.toString() + "' ja existe");
    }
}
