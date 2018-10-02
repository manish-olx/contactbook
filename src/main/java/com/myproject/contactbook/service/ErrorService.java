package com.myproject.contactbook.service;

import com.myproject.contactbook.pojo.ApiError;
import com.myproject.contactbook.pojo.Error;
import com.myproject.contactbook.util.LoggerUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class ErrorService {
    @Autowired
    private ExceptionMessage exceptionMessage;
    @Autowired
    private LoggerUtil loggerUtil;


    public ApiError setErrorMessage(String message) {
        ApiError apiError = new ApiError();
        Error error = new Error();
        apiError.setError(error);
        apiError.getError().setGlobalError(message);
        return apiError;
    }

    public ApiError setErrorMessage(String message, HttpStatus status) {
        ApiError apiError = new ApiError();
        Error error = new Error();
        apiError.setError(error);
        apiError.getError().setGlobalError(message);
        apiError.setStatus(status);
        return apiError;
    }



}
