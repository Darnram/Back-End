package com.danram.server.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ApiExceptionHandler {
    /*@ExceptionHandler(UserNameNotFoundException.class)
    public ResponseEntity<ApiErrorResponse> handleException(UserNameNotFoundException ex) {
        ApiErrorResponse response = new ApiErrorResponse("ERROR-0001","User is not found : nickname is "+ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }*/
}