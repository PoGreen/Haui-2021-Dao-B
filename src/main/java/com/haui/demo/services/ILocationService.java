package com.haui.demo.services;

import com.haui.demo.models.bos.SystemResponse;
import org.springframework.http.ResponseEntity;

public interface ILocationService {
    ResponseEntity<SystemResponse<Object>> getAllProvinces();

    ResponseEntity<SystemResponse<Object>> getDistrictsByProvince(int provinceId);

    ResponseEntity<SystemResponse<Object>> getWardsByDistrict(int districtsId);

    String getLocationByWard(int wardId);
}
