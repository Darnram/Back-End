package com.danram.server.exception.comment;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@ResponseStatus(HttpStatus.NOT_FOUND)
public class CommentNotFoundException extends RuntimeException {
    private String message;

    public CommentNotFoundException(String message) {
        super(message);
        this.message = message;
    }
}
