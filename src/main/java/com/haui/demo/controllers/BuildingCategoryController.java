package com.haui.demo.controllers;

import com.haui.demo.models.bos.SystemResponse;
import com.haui.demo.models.requests.BuildingCategoryRq;
import com.haui.demo.models.requests.StatusRq;
import com.haui.demo.services.IBuildingCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;

@Controller(value = "building-catergories")
public class BuildingCategoryController {

    @Autowired
    private IBuildingCategoryService iBuildingCategoryService;

    @GetMapping("/building-categories")
    public ResponseEntity<SystemResponse<Object>> getALl(HttpServletRequest request) {
        return iBuildingCategoryService.getAll();
    }

    @PostMapping("/building-categories")
    public ResponseEntity<SystemResponse<Object>> addOne(HttpServletRequest request, @RequestBody BuildingCategoryRq buildingCategoryRq) {
        return iBuildingCategoryService.addOne(request, buildingCategoryRq);
    }

    @PutMapping("/building-categories")
    public ResponseEntity<SystemResponse<Object>> updateOne(HttpServletRequest request, @RequestBody StatusRq statusRq) {
        return iBuildingCategoryService.changeStatus(request,statusRq);
    }
}
