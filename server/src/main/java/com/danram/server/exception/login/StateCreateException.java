package com.danram.server.exception.login;

import com.danram.server.exception.ErrorCode;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class StateCreateException extends RuntimeException {
    private String message;
    private ErrorCode code;

    public StateCreateException(String email) {
        super(email);
        this.message = email;
    }
}
