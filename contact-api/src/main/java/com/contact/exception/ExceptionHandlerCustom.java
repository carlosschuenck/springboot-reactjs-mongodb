package com.contact.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
@Slf4j
public class ExceptionHandlerCustom extends ResponseEntityExceptionHandler {

    @ExceptionHandler({ ConstraintViolationException.class })
    public ResponseEntity<Object> handleConstraintViolation(
            ConstraintViolationException ex, WebRequest request) {
        List<String> errors = new ArrayList();
        ex.getConstraintViolations().forEach(violation ->
            errors.add(violation.getRootBeanClass().getName() + " " +
                       violation.getPropertyPath() + ": " +
                       violation.getMessage()));

        ApiExceptionResponse apiExceptionResponse = ApiExceptionResponse.builder().errors(errors).build();
        log.error(ex.getLocalizedMessage(), ex);
        return new ResponseEntity(apiExceptionResponse, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }


    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<String> errors = new ArrayList();
        BindingResult bindingResult = ex.getBindingResult();
        bindingResult.getFieldErrors().forEach(error -> errors.add(error.getDefaultMessage()));
        bindingResult.getGlobalErrors().forEach(error ->  errors.add(error.getObjectName() + ": " + error.getDefaultMessage()));
        ApiExceptionResponse apiExceptionResponse = ApiExceptionResponse.builder().errors(errors).build();
        log.error(ex.getLocalizedMessage(), ex);
        return handleExceptionInternal(ex, apiExceptionResponse, headers, HttpStatus.BAD_REQUEST, request);
    }
}
