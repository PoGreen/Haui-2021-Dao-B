package com.haui.demo.models.bos;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;

public class RestRequest {
    private String url;

    @JsonProperty("http_method")
    private HttpMethod httpMethod;

    private HttpEntity<?> entity;

    private int times;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public HttpMethod getHttpMethod() {
        return httpMethod;
    }

    public void setHttpMethod(HttpMethod httpMethod) {
        this.httpMethod = httpMethod;
    }

    public HttpEntity<?> getEntity() {
        return entity;
    }

    public void setEntity(HttpEntity<?> entity) {
        this.entity = entity;
    }

    public int getTimes() {
        return times;
    }

    public void setTimes(int times) {
        this.times = times;
    }

    public RestRequest() {
    }

    public RestRequest(String url, HttpMethod httpMethod, HttpEntity<?> entity) {
        this.url = url;
        this.httpMethod = httpMethod;
        this.entity = entity;
    }
}
