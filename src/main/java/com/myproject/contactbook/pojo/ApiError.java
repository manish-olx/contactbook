package com.myproject.contactbook.pojo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@Scope(value = "prototype")
public class ApiError {

    @Autowired
    private Error error;

    private HttpStatus status;

    public ApiError() {
    }

    public Error getError() {
        return error;
    }

    public void setError(Error error) {
        this.error = error;
    }

    public HttpStatus getStatus() {
        if (status == null) {
            return HttpStatus.BAD_REQUEST;
        }
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }
}