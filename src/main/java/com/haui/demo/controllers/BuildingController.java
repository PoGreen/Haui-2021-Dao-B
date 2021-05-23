package com.haui.demo.controllers;

import com.haui.demo.models.requests.BuildingFilter;
import com.haui.demo.models.bos.Panigation;
import com.haui.demo.models.bos.SystemResponse;
import com.haui.demo.models.requests.BuildingRq;
import com.haui.demo.models.requests.ExportExcel;
import com.haui.demo.models.requests.StatusRq;
import com.haui.demo.services.IBuildingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotBlank;
import java.io.IOException;

@Controller(value = "buildings")
public class BuildingController {


    @Autowired
    private IBuildingService iBuildingService;

    @GetMapping(value = "/buildings")
    public ResponseEntity<SystemResponse<Object>> getAllShow(@RequestParam(value = "page", defaultValue = "1") int page,
                                                             @RequestParam(value = "limit", defaultValue = "9") int limit) {
        Panigation panigation = new Panigation();
        panigation.setPage(page);
        panigation.setLimit(limit);
        return iBuildingService.getAllShow(panigation);
    }

    @GetMapping(value = "/buildings/filters")
    public ResponseEntity<SystemResponse<Object>> getByFilter(HttpServletRequest request,
                                                              @RequestParam(value = "category", required = false) String buildingCategory,
                                                              @RequestParam(value = "ward", required = false) Integer ward,
                                                              @RequestParam(value = "bedRoom", required = false) Integer bedRoom,
                                                              @RequestParam(value = "function_room", required = false) Integer functionRoom,
                                                              @RequestParam(value = "price", required = false) Long price,
                                                              @RequestParam(value = "floor_area", required = false) Integer floorArea,
                                                              @RequestParam(value = "direction", required = false) String direction,
                                                              @RequestParam(value = "sale_rent", required = false) Integer saleRent,
                                                              @RequestParam(value = "status", required = false) Integer status,
                                                              @RequestParam(value = "page", defaultValue = "1") int page) {

        BuildingFilter filter = new BuildingFilter();
        filter.setPrice(price);
        filter.setBuildingCategory(buildingCategory);
        filter.setBedRoom(bedRoom);
        filter.setFunctionRoom(functionRoom);
        filter.setDirection(direction);
        filter.setFloorArea(floorArea);
        filter.setSaleRent(saleRent);
        filter.setWard(ward);
        filter.setPage(page);
        filter.setStatus(status);
        return iBuildingService.filters(request, filter);
    }

    @PostMapping(value = "/buildings/filters/body")
    public ResponseEntity<SystemResponse<Object>> getByFilterBody(HttpServletRequest request,
                                                                  @RequestBody BuildingFilter filter) {
        return iBuildingService.filters(request, filter);
    }

    @GetMapping(value = "/user/buildings/filters")
    public ResponseEntity<SystemResponse<Object>> getUserBuildingByFilter(HttpServletRequest request,
                                                                          @RequestParam(value = "category", required = false) String buildingCAtegory,
                                                                          @RequestParam(value = "ward", required = false) Integer ward,
                                                                          @RequestParam(value = "bedRoom", required = false) int bedRoom,
                                                                          @RequestParam(value = "function_room", required = false) int functionRoom,
                                                                          @RequestParam(value = "price", required = false) long price,
                                                                          @RequestParam(value = "floor_area", required = false) int floorArea,
                                                                          @RequestParam(value = "direction", required = false) String direction,
                                                                          @RequestParam(value = "sale_rent", required = false) int saleRent,
                                                                          @RequestParam(value = "status", required = false) int status,
                                                                          @RequestParam(value = "page", defaultValue = "1") int page) {

        BuildingFilter filter = new BuildingFilter();
        filter.setPrice(price);
        filter.setBuildingCategory(buildingCAtegory);
        filter.setBedRoom(bedRoom);
        filter.setFunctionRoom(functionRoom);
        filter.setDirection(direction);
        filter.setFloorArea(floorArea);
        filter.setSaleRent(saleRent);
        filter.setWard(ward);
        filter.setPage(page);
        filter.setStatus(status);
        return iBuildingService.userFilters(request, filter);
    }

    @GetMapping(value = "/user/buildings/v2")
    public ResponseEntity<SystemResponse<Object>> getUserBuilding(HttpServletRequest request,
                                                                  @RequestParam(value = "sale_rent", required = false) int saleRent,
                                                                  @RequestParam(value = "status", required = false) int status,
                                                                  @RequestParam(value = "page", defaultValue = "1") int page) {

        Panigation panigation = new Panigation();
        panigation.setPage(page);
        panigation.setLimit(20);
        return iBuildingService.userFilters(request, saleRent, status, panigation);
    }

    @GetMapping(value = "/buildings/excel")
    public ResponseEntity<SystemResponse<String>> exportExcel(HttpServletRequest request,
                                                              @RequestParam(value = "category", required = false) String buildingCAtegory,
                                                              @RequestParam(value = "ward", required = false) Integer ward,
                                                              @RequestParam(value = "bedRoom", required = false) Integer bedRoom,
                                                              @RequestParam(value = "function_room", required = false) Integer functionRoom,
                                                              @RequestParam(value = "price", required = false) Long price,
                                                              @RequestParam(value = "floor_area", required = false) Integer floorArea,
                                                              @RequestParam(value = "direction", required = false) String direction,
                                                              @RequestParam(value = "sale_rent", required = false) Integer saleRent,
                                                              @RequestParam(value = "status", required = false) Integer status,
                                                              @NotBlank @RequestParam(value = "fields") String fields) throws IOException, IllegalAccessException {

        ExportExcel excel = new ExportExcel();
        excel.setPrice(price);
        excel.setBuildingCategory(buildingCAtegory);
        excel.setBedRoom(bedRoom);
        excel.setFunctionRoom(functionRoom);
        excel.setDirection(direction);
        excel.setFloorArea(floorArea);
        excel.setSaleRent(saleRent);
        excel.setWard(ward);
        excel.setStatus(status);
        excel.setFields(fields);
        return iBuildingService.exportExcel(excel);
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
    public ResponseEntity<SystemResponse<Object>> getOne(HttpServletRequest request, @PathVariable(value = "id") String id) {
        return iBuildingService.getOne(request, id);
    }

    @GetMapping(value = "/users/buildings")
    public ResponseEntity<SystemResponse<Object>> getByUser(HttpServletRequest request,
                                                            @RequestParam(value = "page", defaultValue = "1") int page,
                                                            @RequestParam(value = "limit", defaultValue = "20") int limit) {
        Panigation panigation = new Panigation();
        panigation.setPage(page);
        panigation.setLimit(limit);
        return iBuildingService.getAllByUser(request, panigation);
    }

    @PostMapping(value = "/user/buildings/send-email")
    public ResponseEntity<SystemResponse<Object>> likeBuilding(HttpServletRequest request, @RequestParam(value = "building-id") String buildingId) {
        return iBuildingService.likeBuilding(request, buildingId);
    }
}
