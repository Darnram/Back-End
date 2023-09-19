package com.danram.server.exception.memberlike;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@ResponseStatus(HttpStatus.NOT_FOUND)
public class MemberLikeNotFoundException extends RuntimeException {
    public MemberLikeNotFoundException(Long type,Long id) {
        super(String.format("member like not found type:%d id:%d",type,id));
    }
}
