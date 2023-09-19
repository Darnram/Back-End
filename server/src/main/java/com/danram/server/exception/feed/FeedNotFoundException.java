package com.danram.server.exception.feed;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@ResponseStatus(HttpStatus.NOT_FOUND)
public class FeedNotFoundException extends RuntimeException {
    private String message;

    public FeedNotFoundException(String message) {
        super(message);
        this.message = message;
    }
}
