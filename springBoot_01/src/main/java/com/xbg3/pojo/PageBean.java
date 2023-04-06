package com.xbg3.pojo;

import java.util.List;

public class PageBean<T> {
    private List<T> pageData;
    private int totalPages;



    public List<T> getPageData() {
        return pageData;
    }

    public void setPageData(List<T> pageData) {
        this.pageData = pageData;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }
}
