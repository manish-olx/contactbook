package com.myproject.contactbook.pojo.response;


public interface Response<T> extends ErrorResponse {

    T getData();

    T getMetadata();

}
