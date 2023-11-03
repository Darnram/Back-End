package com.danram.server.exception;

import com.danram.server.exception.chatting.ChattingRoomDataNotExistException;
import com.danram.server.exception.member.MemberLoginTypeNotExistException;
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
    /**
     * User Error DEU => Danram User Error
     * */
    @ExceptionHandler(MemberNameNotFoundException.class)
    public ResponseEntity<ApiErrorResponse> handleException(MemberNameNotFoundException ex) {
        ApiErrorResponse response = new ApiErrorResponse("DEU-0001","User is not found : nickname is "+ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MemberIdNotFoundException.class)
    public ResponseEntity<ApiErrorResponse> handleException(MemberIdNotFoundException ex) {
        ApiErrorResponse response = new ApiErrorResponse("DEU-0002","user is not found - id "+ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NotPartyManagerException.class)
    public ResponseEntity<ApiErrorResponse> handleException(NotPartyManagerException ex) {
        ApiErrorResponse response = new ApiErrorResponse("DEU-0003","member id : "+ex.getMessage()+" not manager");
        return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
    }

    /**
     * Party Error DEP => Danram Party Error
     * */
    @ExceptionHandler(PartyNotFoundException.class)
    public ResponseEntity<ApiErrorResponse> handleException(PartyNotFoundException ex) {
        ApiErrorResponse response = new ApiErrorResponse("DEP-0001","Party is not found - id "+ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(PartyMemberNotFoundException.class)
    public ResponseEntity<ApiErrorResponse> handleException(PartyMemberNotFoundException ex) {
        ApiErrorResponse response = new ApiErrorResponse("DEP-0002","party member not found - party or party-member id "+ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(PartyHostExitException.class)
    public ResponseEntity<ApiErrorResponse> handleException(PartyHostExitException ex) {
        ApiErrorResponse response  = new ApiErrorResponse("DEP-0003","party host cannot exit party - member id "+ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(PartyMemberDuplicatedException.class)
    public ResponseEntity<ApiErrorResponse> handleException(PartyMemberDuplicatedException ex) {
        ApiErrorResponse response = new ApiErrorResponse("DEP-0004","party member duplicated "+ex.getMessage());
        return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(PartyJoinNotAllowException.class)
    public ResponseEntity<ApiErrorResponse> handleException(PartyJoinNotAllowException ex) {
        ApiErrorResponse response = new ApiErrorResponse("DEP-0005",ex.getMessage());
        return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
    }

    /**
     * Token Error DET => Danram Token Error
     * */
    @ExceptionHandler(TokenNotFoundException.class)
    public ResponseEntity<ApiErrorResponse> handleException(TokenNotFoundException ex) {
        ApiErrorResponse response = new ApiErrorResponse("DET-0001",ex.getMessage());
        return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
    }

    /**
     * Login Error DEL => Danram Login Error
     * */
    @ExceptionHandler(MemberLoginTypeNotExistException.class)
    public ResponseEntity<ApiErrorResponse> handleException(MemberLoginTypeNotExistException ex) {
        ApiErrorResponse response = new ApiErrorResponse("DEL-001", "login type: " + ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    /**
     * chatting error DEC
     * */
    @ExceptionHandler(ChattingRoomDataNotExistException.class)
    public ResponseEntity<ApiErrorResponse> handleException(ChattingRoomDataNotExistException ex) {
        ApiErrorResponse response = new ApiErrorResponse("DEC-001", "chatting error: " + ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    /**
     * Unknown Error
     * */
    @ExceptionHandler(InvalidHostException.class)
    public ResponseEntity<ApiErrorResponse> handleException(InvalidHostException ex) {
        ApiErrorResponse response = new ApiErrorResponse("DE-0001","invalid host request - id "+ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
