package com.myproject.contactbook.pojo.response.userContact;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.myproject.contactbook.pojo.ApiError;
import com.myproject.contactbook.pojo.response.ListResponse;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserContactItemResponse implements ListResponse {

    private List<UserContactData> data;
    private ApiError error;

    @Override
    public List<UserContactData> getData() {
        return data;
    }

    public void setData(List<UserContactData> data) {
        this.data = data;
    }

    @Override
    public ApiError getError() {
        return error;
    }

    @Override
    public void setError(ApiError error) {
        this.error = error;
    }

    @Override
    public List getMetadata() {
        return null;
    }

}
