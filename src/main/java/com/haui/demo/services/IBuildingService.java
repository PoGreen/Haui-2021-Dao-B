package com.haui.demo.services;

import com.haui.demo.models.bos.Panigation;
import com.haui.demo.models.bos.SystemResponse;
import com.haui.demo.models.requests.BuildingFilter;
import com.haui.demo.models.requests.BuildingRq;
import com.haui.demo.models.requests.StatusRq;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;

public interface IBuildingService {

    ResponseEntity<SystemResponse<Object>> getAllByUser(HttpServletRequest request, Panigation panigation);

    ResponseEntity<SystemResponse<Object>> filters(HttpServletRequest request, BuildingFilter filter);

    ResponseEntity<SystemResponse<Object>> getAllShow(Panigation panigation);

    ResponseEntity<SystemResponse<Object>> addOne(HttpServletRequest request, BuildingRq buildingRq);

    ResponseEntity<SystemResponse<Object>> update(HttpServletRequest request, BuildingRq buildingRq);

    ResponseEntity<SystemResponse<Object>> changeStatus(HttpServletRequest request, StatusRq statusRq);

    ResponseEntity<SystemResponse<Object>> getOne(HttpServletRequest request, String id);
}
