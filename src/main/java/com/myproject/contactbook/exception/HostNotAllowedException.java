package com.myproject.contactbook.exception;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class HostNotAllowedException extends Exception {


    @Autowired
    public HostNotAllowedException(@Value("${exception.hostnotallowed}") String failedMessage) {
        super(failedMessage);
    }

}
