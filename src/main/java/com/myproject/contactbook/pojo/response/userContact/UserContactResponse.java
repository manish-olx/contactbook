package com.myproject.contactbook.pojo.response.userContact;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.myproject.contactbook.pojo.ApiError;
import com.myproject.contactbook.pojo.response.Response;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserContactResponse implements Response {

    private UserContactActionData data;
    private UserContactMetaData metadata;
    private ApiError error;

    @Override
    public UserContactActionData getData() {
        return data;
    }

    public void setData(UserContactActionData data) {
        this.data = data;
    }

    @Override
    public UserContactMetaData getMetadata() {
        return metadata;
    }

    public void setMetadata(UserContactMetaData metadata) {
        this.metadata = metadata;
    }

    @Override
    public ApiError getError() {
        return error;
    }

    @Override
    public void setError(ApiError error) {
        this.error = error;
    }
}
