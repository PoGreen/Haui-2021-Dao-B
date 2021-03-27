package com.haui.demo.controllers;

import com.haui.demo.models.bos.Panigation;
import com.haui.demo.models.bos.SystemResponse;
import com.haui.demo.models.requests.BuildingRq;
import com.haui.demo.models.requests.StatusRq;
import com.haui.demo.services.IBuildingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller(value = "buildings")
public class BuildingController {


    @Autowired
    private IBuildingService iBuildingService;

    @GetMapping(value = "/buildings")
    public ResponseEntity<SystemResponse<Object>> getAllShow(@RequestParam(value = "page", defaultValue = "1") int page,
                                                             @RequestParam(value = "limit", defaultValue = "6") int limit) {
        Panigation panigation = new Panigation();
        panigation.setPage(page);
        panigation.setLimit(limit);
        return iBuildingService.getAllShow(panigation);
    }

    @PostMapping(value = "/buildings")
    public ResponseEntity<SystemResponse<Object>> addOne(HttpServletRequest request, @RequestBody BuildingRq buildingRq) {
        return iBuildingService.addOne(request, buildingRq);
    }

    @PutMapping(value = "/buildings")
    public ResponseEntity<SystemResponse<Object>> updateOne(HttpServletRequest request, @RequestBody BuildingRq buildingRq) {
        return iBuildingService.update(request, buildingRq);
    }

    @PutMapping(value = "/buildings/status")
    public ResponseEntity<SystemResponse<Object>> changeStatus(HttpServletRequest request, @RequestBody StatusRq statusRq) {
        return iBuildingService.changeStatus(request, statusRq);
    }

    @GetMapping(value = "/buildings/{id}")
    public ResponseEntity<SystemResponse<Object>> getOne(HttpServletRequest request,@PathVariable   (value = "id") String id) {
        return iBuildingService.getOne(request,id);
    }
    @GetMapping(value = "/users/buildings")
    public ResponseEntity<SystemResponse<Object>> getByUser(HttpServletRequest request,
                                                            @RequestParam(value = "page") int page,
                                                            @RequestParam(value = "limit") int limit) {
        Panigation panigation = new Panigation();
        panigation.setPage(page);
        panigation.setLimit(limit);
        return iBuildingService.getAllByUser(request,panigation);
    }
}
