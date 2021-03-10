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
    String ROLE_IS_FAKE = "role is fake";
    // locations
    String PROVINCE_IS_FAKE = "province is fake";
    String DISTRICT_IS_FAKE = "district is fake";

    String NAME_IS_EXIST= "name is exists";

    String ID_IS_FAKE= "ID is FAKE";
    String STATUS_IS_FAKE= "ID is FAKE";

    String FILE_IS_NOT_IMAGE = "file is not image";
    String FILE_IS_NOT_EXISTS = "file is not exists";

    String BUILDING_CATEGORY_IS_FAKE = "building category is fake";
    String BUILDING_IS_FAKE = "building is fake";
    String USER_HAS_NOT_IT = "user does not has it";
}
