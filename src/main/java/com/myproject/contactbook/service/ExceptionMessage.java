package com.myproject.contactbook.service;

import org.springframework.stereotype.Component;

@Component
public class ExceptionMessage {
    public String getMessage(Exception e) {
        String message = "";
        if (e instanceof NullPointerException) {
            message = e.getStackTrace()[0].toString();
        } else {
            message = e.getMessage();
        }
        return message;
    }
}
