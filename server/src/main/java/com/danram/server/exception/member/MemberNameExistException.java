package com.danram.server.exception.member;

import com.danram.server.exception.ErrorCode;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class MemberNameExistException extends RuntimeException {
    private String message;
    private ErrorCode code;

    public MemberNameExistException(String name) {
        super(name);
        message = name;
    }
}
