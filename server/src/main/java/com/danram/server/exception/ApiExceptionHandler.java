package com.danram.server.exception;

import com.danram.server.exception.party.*;
import com.danram.server.exception.token.TokenNotFoundException;
import com.danram.server.exception.member.MemberIdNotFoundException;
import com.danram.server.exception.member.MemberNameNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ApiExceptionHandler {
    @ExceptionHandler(MemberNameNotFoundException.class)
    public ResponseEntity<ApiErrorResponse> handleException(MemberNameNotFoundException ex) {
        ApiErrorResponse response = new ApiErrorResponse("ERROR-0001","User is not found : nickname is "+ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(PartyNotFoundException.class)
    public ResponseEntity<ApiErrorResponse> handleException(PartyNotFoundException ex) {
        ApiErrorResponse response = new ApiErrorResponse("ERROR-0002","Party is not found - id "+ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MemberIdNotFoundException.class)
    public ResponseEntity<ApiErrorResponse> handleException(MemberIdNotFoundException ex) {
        ApiErrorResponse response = new ApiErrorResponse("ERROR-0003","user is not found - id "+ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidHostException.class)
    public ResponseEntity<ApiErrorResponse> handleException(InvalidHostException ex) {
        ApiErrorResponse response = new ApiErrorResponse("ERROR-0004","invalid host request - id "+ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(PartyMemberNotFoundException.class)
    public ResponseEntity<ApiErrorResponse> handleException(PartyMemberNotFoundException ex) {
        ApiErrorResponse response = new ApiErrorResponse("ERROR-0005","party member not found - party or party-member id "+ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(PartyHostExitException.class)
    public ResponseEntity<ApiErrorResponse> handleException(PartyHostExitException ex) {
        ApiErrorResponse response  = new ApiErrorResponse("ERROR-0006","party host cannot exit party - member id "+ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(TokenNotFoundException.class)
    public ResponseEntity<ApiErrorResponse> handleException(TokenNotFoundException ex) {
        ApiErrorResponse response = new ApiErrorResponse("ERROR-0007",ex.getMessage());
        return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(PartyMemberDuplicatedException.class)
    public ResponseEntity<ApiErrorResponse> handleException(PartyMemberDuplicatedException ex) {
        ApiErrorResponse response = new ApiErrorResponse("ERROR-0008","party member duplicated "+ex.getMessage());
        return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(PartyJoinNotAllowException.class)
    public ResponseEntity<ApiErrorResponse> handleException(PartyJoinNotAllowException ex) {
        ApiErrorResponse response = new ApiErrorResponse("ERROR-0009",ex.getMessage());
        return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
    }
}
