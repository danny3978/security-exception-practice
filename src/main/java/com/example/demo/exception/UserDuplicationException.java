package com.example.demo.exception;

public class UserDuplicationException extends RuntimeException{
    public UserDuplicationException(String errorMessage) {
        super(errorMessage);
    }
}
