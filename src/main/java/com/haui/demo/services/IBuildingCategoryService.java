package com.haui.demo.services;

import com.haui.demo.models.bos.SystemResponse;
import com.haui.demo.models.requests.BuildingCategoryRq;
import com.haui.demo.models.requests.StatusRq;
import io.swagger.models.auth.In;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;

public interface IBuildingCategoryService {

    ResponseEntity<SystemResponse<Object>> getOne(HttpServletRequest request);

    ResponseEntity<SystemResponse<Object>> getAll(Integer status);

    ResponseEntity<SystemResponse<Object>> addOne(HttpServletRequest request, BuildingCategoryRq buildingCategoryRq);

    ResponseEntity<SystemResponse<Object>> changeStatus(HttpServletRequest request, StatusRq statusRq);
}
