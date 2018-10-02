package com.myproject.contactbook.exception;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseStatus;

@Component
@ResponseStatus(HttpStatus.NOT_FOUND)
public class ContactNotFoundException extends Exception {

    @Autowired
    public ContactNotFoundException(@Value("${exception.contactNotFound}") String message) {
        super(message);
    }
}
