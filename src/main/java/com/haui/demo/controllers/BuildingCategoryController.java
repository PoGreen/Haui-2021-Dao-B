package com.haui.demo.controllers;

import com.haui.demo.models.bos.SystemResponse;
import com.haui.demo.models.requests.BuildingCategoryRq;
import com.haui.demo.models.requests.StatusRq;
import com.haui.demo.services.IBuildingCategoryService;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller(value = "building-catergories")
public class BuildingCategoryController {

    @Autowired
    private IBuildingCategoryService iBuildingCategoryService;

    @GetMapping("/building-categories")
    public ResponseEntity<SystemResponse<Object>> getALl(@RequestParam(value = "status") Integer status) {
        return iBuildingCategoryService.getAll(status);
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
