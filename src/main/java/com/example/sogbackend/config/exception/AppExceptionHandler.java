package com.example.sogbackend.config.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class AppExceptionHandler {

    @ExceptionHandler(value = {SogException.class})
    public ResponseEntity<Object> HandlerSogException(SogException ex, WebRequest request){
        return new ResponseEntity<>(ex.getMessage(), new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
