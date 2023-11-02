package com.danram.server.exception.chatting;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@ResponseStatus(HttpStatus.NOT_FOUND)
public class ChattingRoomDataNotExistException extends RuntimeException {
    private String message;

    public ChattingRoomDataNotExistException(String roomId) {
        super(roomId);
        this.message = roomId;
    }
}
