package com.myproject.contactbook.exception;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseStatus;

@Component
@ResponseStatus(HttpStatus.FORBIDDEN)
public class ContactAlreadyExistsException extends Exception {

    @Autowired
    public ContactAlreadyExistsException(@Value("${exception.contactAlreadyExists}") String message) {
        super(message);
    }
}
