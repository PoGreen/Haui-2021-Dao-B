package com.haui.demo.utils;

public interface StringResponse {
    String UNAUTHORIZED = "UNAUTHORIZED!";
    String SUCCESS = "OK!";
    String INVALID_TOKEN = "invalid token!";
    String NOT_FOUND = "not found";
    String BAD_REQUEST = "invalid request";
    String OTP_FALSE = "otp was generated false";
    String OTP_NOT_FOUND = "otp is not found";
    String OTP_REQUEST = "the input was not valid ";

    //category
    String CATEGORY_NOT_FOUND = "category is not exist";
    String OPTION_CONDITION_ERROR = "Option create fields is not null";

    // profile
    String AGE_ENOUGH = "user is not enough age to get account";
    String IMAGES_ENOUGH = "images upload are not enough to update profile";
    String QUESTION_ENOUGH = "questions is not enough to update profile";
    String USER_ID_FAKE = "user id is fake";
    String USER_NAME_IS_EXIST = "username is exists";
    String INVALID_MULTIPART_FILES = "invalid multipart file !";

    //location
    String WARD_IS_FAKE = "ward is fake";

}
