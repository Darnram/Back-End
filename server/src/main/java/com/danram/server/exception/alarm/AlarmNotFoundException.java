package com.danram.server.exception.alarm;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@ResponseStatus(HttpStatus.NOT_FOUND)
public class AlarmNotFoundException extends RuntimeException {
    private String message;

    public AlarmNotFoundException(String message) {
        super(message);
        this.message = message;
    }
}
