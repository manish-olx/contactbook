package com.myproject.contactbook.pojo;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@Scope(value = "prototype")
public class Error {
    private String domainSuggestions;
    private String globalError;
    private int tried;


    private ArrayList<FieldValidation> fieldErrors;

    public Error() {
    }

    public String getDomainSuggestions() {
        if (domainSuggestions == null) {
            return "";
        }
        return domainSuggestions;
    }

    public void setDomainSuggestions(String domainSuggestions) {
        this.domainSuggestions = domainSuggestions;
    }

    public String getGlobalError() {
        return globalError;
    }

    public void setGlobalError(String globalError) {
        this.globalError = globalError;
    }

    public int getTried() {
        return tried;
    }

    public void setTried(int tried) {
        this.tried = tried;
    }

    public ArrayList<FieldValidation> getFieldErrors() {
        if (fieldErrors == null) {
            fieldErrors = new ArrayList<FieldValidation>();
            //add(new FieldValidation());
        }
        return fieldErrors;
    }

    public void setFieldErrors(ArrayList<FieldValidation> fieldErrors) {
        this.fieldErrors = fieldErrors;
    }
}
