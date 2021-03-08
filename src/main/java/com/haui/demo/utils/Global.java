package com.haui.demo.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.haui.demo.models.bos.SystemResponse;
import jdk.internal.icu.impl.UCharacterProperty;
import org.apache.logging.log4j.Logger;
import org.springframework.core.ParameterizedTypeReference;

import java.util.Map;

public class Global {

    public static final Gson gson;
    public static final ObjectMapper objectMapper;
    public static final String BEARER = "Bearer";
    public static final String AUTHORIZATION = "Authorization";
    public static final String USER_ATTR = "user";
    public static final String SEPARATOR = ":";
    public static final String CREDENTIAL_ATTR = "credential";
    public static final String SECRET_KEY = "secret-key";
    public static final ParameterizedTypeReference<SystemResponse<Object>> mapObject;
    public static final ParameterizedTypeReference<Map<String, Object>> mapOTPResponse;

    public static final Integer ACTIVE = 1;
    public static final Integer NOACTIVE = 0;
    public static final Integer WAIT = 2;

    public static final String ROLE_USER = "USER";
    public static Logger logger;
    public static UCharacterProperty errorTypeRef;


    static {
        gson = new Gson();
        objectMapper = new ObjectMapper();
        mapObject = new ParameterizedTypeReference<SystemResponse<Object>>() {
        };
        mapOTPResponse = new ParameterizedTypeReference<Map<String, Object>>() {
        };

    }

    public static String mapToString(Object o) {
        try {
            return objectMapper.writeValueAsString(o);
        } catch (JsonProcessingException e) {
            logger.error(e);
            return null;
        }
    }
}
