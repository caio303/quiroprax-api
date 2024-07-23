package com.quiroprax.api.infra.errors.exceptions;

import org.springframework.http.HttpStatus;

public class OperationErrorException extends BaseHttpMappedException {

    @Override
    public HttpStatus getHttpStatus() {
        return HttpStatus.BAD_REQUEST;
    }

    public OperationErrorException(String mensagem) {
        super(mensagem);
    }

    public OperationErrorException(String operacao, String causa) {
        super(operacao + " falhou, causa: '" + causa + "'");
    }

}
