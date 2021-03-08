package com.haui.demo.utils;

public interface StatusResponse {

    int OK = 200;
    int BAD_REQUEST = 404;
    int UNAUTHORIZED = 401;
    int FORBIDDEN = 403;
    int FILE_TOO_LARGE = 413;
    int FILE_ERROR = 405;
    int OBJECT_NULL = 600;
    int CONDITION_NOT_ENOUGH = 601;
    int NOT_SUCCESS = 602;

    // profile status

    int CREATED = 201;
    int AGE_ENOUGH = 610;
    int IMAGES_ENOUGH = 611;
    int QUESTION_ENOUGH = 612;
    int USER_ID_FAKE = 613;


}
