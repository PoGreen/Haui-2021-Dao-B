package com.haui.demo.models.bos;

import com.haui.demo.utils.Global;

public class SystemResponse<T> {

    private int status;

    private String message;

    private T data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public SystemResponse() {
    }

    public SystemResponse(int status) {
        this.status = status;
    }

    public SystemResponse(int status, String message, T data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public SystemResponse(int status, String message) {
        this.status = status;
        this.message = message;
    }

    @Override
    public String toString() {
        SystemResponse<?> response = new SystemResponse<>(this.status, this.message, this.data);
        return Global.gson.toJson(response);
    }
}
