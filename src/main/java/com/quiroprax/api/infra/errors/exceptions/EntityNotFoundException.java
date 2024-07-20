package com.quiroprax.api.infra.errors.exceptions;

import org.springframework.http.HttpStatus;

public class EntityNotFoundException extends BaseHttpMappedException {

    @Override
    public HttpStatus getHttpStatus() {
        return HttpStatus.NOT_FOUND;
    }

    public <T> EntityNotFoundException(Class<T> entityClass) {
        super(entityClass.getSimpleName() + " nao encontrado");
    }

    public <T> EntityNotFoundException(Class<T> entityClass, String fieldName, Object value) {
        super(entityClass.getSimpleName() + " com '" + fieldName + "' igual a '" +value.toString() + "' nao encontrado.");
    }


}
