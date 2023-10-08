package com.danram.server.exception.member;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@ResponseStatus(HttpStatus.NOT_FOUND)
public class PhoneNumberNotFoundException extends RuntimeException {
    private String message;

    public PhoneNumberNotFoundException(String ph) {
        super(ph);
        this.message = ph;
    }
}
