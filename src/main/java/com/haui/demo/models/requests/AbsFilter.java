package com.haui.demo.models.requests;

import javax.servlet.http.HttpServletRequest;
import java.time.ZonedDateTime;

public abstract class AbsFilter {

    protected String username;
    protected HttpServletRequest request;
    protected ZonedDateTime startAt;
    protected ZonedDateTime endAt;
    protected int page;
    protected int size;


    protected AbsFilter() {
    }

    public HttpServletRequest getRequest() {
        return request;
    }

    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public ZonedDateTime getStartAt() {
        return startAt;
    }

    public void setStartAt(ZonedDateTime startAt) {
        this.startAt = startAt;
    }

    public ZonedDateTime getEndAt() {
        return endAt;
    }

    public void setEndAt(ZonedDateTime endAt) {
        this.endAt = endAt;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

}
