package com.haui.demo.exceptions;

import org.springframework.http.HttpStatus;

public class HttpErrorException extends Exception {
    private HttpStatus status;
    private String message;

    protected HttpErrorException(HttpStatus status, String message) {
        super(message);
        this.status = status;
        this.message = message;
    }

    private HttpErrorException(Exception e) {
        super(e);
        this.status = HttpStatus.INTERNAL_SERVER_ERROR;
        this.message = "Lỗi hệ thống. Vui lòng thử lại sau";
    }

    public static HttpErrorException from(Exception e) {
        return new HttpErrorException(e);
    }

    public static HttpErrorException from(HttpStatus status, String message) {
        return new HttpErrorException(status, message);
    }

    public static HttpErrorException badRequest(String msg) {
        return from(HttpStatus.BAD_REQUEST, msg);
    }

    public static HttpErrorException unauthorized(String msg) {
        return from(HttpStatus.UNAUTHORIZED, msg);
    }

    public static HttpErrorException unauthorized() {
        return from(HttpStatus.UNAUTHORIZED, "unauthorized");
    }

    public static HttpErrorException conflict(String msg) {
        return from(HttpStatus.CONFLICT, msg);
    }

    public static HttpErrorException forbidden() {
        return from(HttpStatus.FORBIDDEN, "Phiên đăng nhập không hợp lệ");
    }

    public static HttpErrorException notFound(String msg) {
        return from(HttpStatus.NOT_FOUND, msg);
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
