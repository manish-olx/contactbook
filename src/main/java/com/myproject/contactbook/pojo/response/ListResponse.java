package com.myproject.contactbook.pojo.response;

import java.util.List;

public interface ListResponse<T> extends ErrorResponse {

    List<T> getData();

    List<T> getMetadata();

}
