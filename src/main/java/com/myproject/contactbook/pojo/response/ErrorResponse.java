package com.myproject.contactbook.pojo.response;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.myproject.contactbook.pojo.ApiError;

public interface ErrorResponse {
    @JsonUnwrapped
    ApiError getError();
    void setError(ApiError apiError);
}
