package com.example.demo.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserDuplicationException.class)
    public ResponseEntity<RestApiException> UserDuplicationException(UserDuplicationException e) {
        RestApiException restApiException = new RestApiException(e.getMessage(), HttpStatus.CONFLICT.value());
        return new ResponseEntity<>(restApiException, HttpStatus.CONFLICT);
    }
}
