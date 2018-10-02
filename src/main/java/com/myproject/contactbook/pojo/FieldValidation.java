package com.myproject.contactbook.pojo;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
@Scope(value = "prototype")
public class FieldValidation {
    private String field;
    private String message;

    public FieldValidation(String field, String defaultMessage) {
        this.field = field;
        this.message = defaultMessage;
    }

    public FieldValidation() {

    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
