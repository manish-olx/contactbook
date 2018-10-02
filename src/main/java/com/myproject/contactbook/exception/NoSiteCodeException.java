package com.myproject.contactbook.exception;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseStatus;

@Component
@ResponseStatus(HttpStatus.FORBIDDEN)
public class NoSiteCodeException extends Exception {

    public NoSiteCodeException(@Value("${exception.nositecode}") String message) {
        super(message);
    }

}
