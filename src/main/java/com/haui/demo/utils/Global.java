package com.haui.demo.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.haui.demo.models.bos.SystemResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.core.ParameterizedTypeReference;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
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
    public static final Integer NOACTIVE = 3;
    public static final Integer WAIT = 2;

    public static final String ROLE_USER = "USER";
    public static Logger logger;


    public static  final String  PATH_FILE_IMAGE = "/home/green/Data/Haui/B-Image/";
    public static final String DOMAIN_PATH_FILE_IMAGE="http://localhost:8888/";
    public static final String BUILDINGS = "building";
    public static final String NEWS = "news";

    public static final String PROVINCES = "provinces";
    public static final String DISTRICTS = "districts";
    public static final String WARDS = "wards";

    public static Map<String, String> mapNameExcel = new HashMap<>();
    static {
        gson = new Gson();
        objectMapper = new ObjectMapper();
        mapObject = new ParameterizedTypeReference<SystemResponse<Object>>() {
        };
        mapOTPResponse = new ParameterizedTypeReference<Map<String, Object>>() {
        };

    }

    static {
        // set name excel in export excel
        mapNameExcel.put("name", "Tên");
        mapNameExcel.put("title", "Tiêu đề");
        mapNameExcel.put("carPark", "Giữ Ôtô");
        mapNameExcel.put("motoPark", "Giữ Môtô");
        mapNameExcel.put("floorArea", "Diện tích sàn");
        mapNameExcel.put("homeFrontage", "Mặt tiền");
        mapNameExcel.put("numberFloor", "Số tầng");
        mapNameExcel.put("frequence", "Tầng số");
        mapNameExcel.put("bedroom", "Phòng ngủ");
        mapNameExcel.put("functionRoom", "Phòng chức năng");
        mapNameExcel.put("altarRoom", "Phòng thờ");
        mapNameExcel.put("price", "Giá");
        mapNameExcel.put("campusArea", "Khuân viên");
        mapNameExcel.put("direction", "Phương hướng");
        mapNameExcel.put("electricityPrice", "Tiền điện");
        mapNameExcel.put("waterPrice", "Tiền nước");
        mapNameExcel.put("servicePrice", "Tiền dịch vụ");
        mapNameExcel.put("homeDeposit", "Tiền cọc");
        mapNameExcel.put("address", "Địa chỉ");
        mapNameExcel.put("saleRent", "Trạng thái");
            mapNameExcel.put("buildingCategory", "Thể loại bất động sản");

    }

    public static final List<String> fieldValidBuilding = Arrays.asList
            ("name", "title", "carPark", "motoPark", "floorArea", "homeFrontage", "numberFloor", "bedroom",
                    "functionRoom", "altarRoom", "price", "campusArea", "direction", "electricityPrice", "frequence", "waterPrice",
                    "servicePrice","homeDeposit","address","buildingCategory");

    public static String mapToString(Object o) {
        try {
            return objectMapper.writeValueAsString(o);
        } catch (JsonProcessingException e) {
            logger.error(e);
            return null;
        }
    }
}
