package com.danram.server.exception.party;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@ResponseStatus(HttpStatus.NOT_FOUND)
public class PartyNotFoundException extends RuntimeException {
    private String message;

    public PartyNotFoundException(String message) {
        super(message);
        this.message = message;
    }
}
