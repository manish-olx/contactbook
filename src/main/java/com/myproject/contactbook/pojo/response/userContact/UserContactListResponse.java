package com.myproject.contactbook.pojo.response.userContact;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.myproject.contactbook.pojo.ApiError;
import com.myproject.contactbook.pojo.response.PageInfo;
import com.myproject.contactbook.pojo.response.Response;
import org.springframework.data.domain.Page;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserContactListResponse implements Response {

    private Page<UserContactData> data;
    private List<MetaWrapper> metadata;
    private ApiError error;

    @Override
    public List<UserContactData> getData() {
        return data.getContent();
    }

    public void setData(Page<UserContactData> data) {
        this.data = data;
    }

    @Override
    public List<MetaWrapper> getMetadata() {
        return metadata;
    }

    public void setMetadata(List<MetaWrapper> metadata) {
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

    public static class MetaWrapper {

        @JsonProperty("page_info")
        private PageInfo pageInfo;

        public PageInfo getPageInfo() {
            return pageInfo;
        }

        public void setPageInfo(PageInfo pageInfo) {
            this.pageInfo = pageInfo;
        }
    }

}
