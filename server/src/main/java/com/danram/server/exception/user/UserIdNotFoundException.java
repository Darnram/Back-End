package com.danram.server.exception.user;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserIdNotFoundException extends RuntimeException {
    private String message;
    public UserIdNotFoundException(String id) {
        super(id);
        this.message = id;
    }
}
