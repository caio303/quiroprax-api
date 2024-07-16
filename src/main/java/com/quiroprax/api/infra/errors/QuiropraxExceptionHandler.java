package com.quiroprax.api.infra.errors;

import com.quiroprax.api.infra.errors.exceptions.BaseHttpMappedException;
import com.quiroprax.api.infra.errors.exceptions.InvalidTokenException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.NoSuchElementException;

@RestControllerAdvice
public class QuiropraxExceptionHandler {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity handleBadRequest(MethodArgumentNotValidException ex) {
		var errors = ex.getFieldErrors();
		return ResponseEntity.badRequest().body(errors.stream().map(FieldValidationError::new).toList());
	}

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity handleBadRequest2(HttpMessageNotReadableException ex) {
        return ResponseEntity.badRequest().body(new Error(HttpStatus.BAD_REQUEST, ex.getMessage()));
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity notFoundException(HttpMessageNotReadableException ex) {
        return ResponseEntity.badRequest().body(new Error(HttpStatus.NOT_FOUND, ex.getMessage()));
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity handleBadCredentials() {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new Error(HttpStatus.UNAUTHORIZED, "Credenciais inválidas"));
    }

    @ExceptionHandler({AuthenticationException.class, InvalidTokenException.class})
    public ResponseEntity handleAuthenticationError() {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new Error(HttpStatus.UNAUTHORIZED, "Falha na autenticação"));
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity handleAccessDenied() {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new Error(HttpStatus.FORBIDDEN, "Acesso negado"));
    }

    @ExceptionHandler(BaseHttpMappedException.class)
    public ResponseEntity handleException(BaseHttpMappedException ex) {
        return ResponseEntity.status(ex.getHttpStatus()).body(new Error(ex.getHttpStatus(), ex.getLocalizedMessage()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity handleException(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Error("Ocorreu um erro interno, entre em contato ou tente mais tarde..."));
    }

	private record Error (Integer errorCode, String message) {
		public Error(HttpStatus httpStatus, String message) { this(httpStatus.value(), message); }
		public Error(String message) {	this(HttpStatus.INTERNAL_SERVER_ERROR.value(), message); }
	}
	
	private record FieldValidationError(String field, String message) {
		public FieldValidationError(FieldError fe) { this(fe.getField(), fe.getDefaultMessage()); }
	}
}