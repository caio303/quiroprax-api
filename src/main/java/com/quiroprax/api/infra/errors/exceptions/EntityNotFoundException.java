package com.quiroprax.api.infra.errors.exceptions;

import org.springframework.http.HttpStatus;

import java.util.Arrays;

public class EntityNotFoundException extends BaseHttpMappedException {

    @Override
    public HttpStatus getHttpStatus() {
        return HttpStatus.NOT_FOUND;
    }

    public <T> EntityNotFoundException(Class<T> entityClass) {
        super(entityClass.getSimpleName() + " nao encontrado(a)");
    }

    public EntityNotFoundException(Class... entityClass) {
        super(String.join(" ou ", Arrays.asList(entityClass) + " nao encontrado(a)"));
    }

    public <T> EntityNotFoundException(Class<T> entityClass, String fieldName, Object value) {
        super(entityClass.getSimpleName() + " com '" + fieldName + "' igual a '" +value.toString() + "' nao encontrado(a)");
    }


}
