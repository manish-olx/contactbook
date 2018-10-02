package com.myproject.contactbook.pojo.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public class PageInfo {

    @JsonProperty("current_page")
    private int currentPage;

    @JsonProperty("page_size")
    private int pageSize;

    @JsonProperty("total_pages")
    private int totalPages;

    @JsonProperty("total_items")
    private long totalItems;

    public void updatePageInfo(Page<?> page, Pageable pageable) {
        this.currentPage = pageable.getPageNumber();
        this.pageSize = pageable.getPageSize();
        this.totalPages = page.getTotalPages();
        this.totalItems = page.getTotalElements();
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public long getTotalItems() {
        return totalItems;
    }
}
