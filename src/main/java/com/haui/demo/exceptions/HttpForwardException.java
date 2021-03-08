package com.haui.demo.exceptions;

import org.springframework.http.HttpStatus;

public class HttpForwardException extends HttpErrorException {

    private HttpForwardException(HttpStatus status, String message) {
        super(status, message);
    }

    public static HttpForwardException from(HttpStatus status, String message) {
        return new HttpForwardException(status, message);
    }
}
