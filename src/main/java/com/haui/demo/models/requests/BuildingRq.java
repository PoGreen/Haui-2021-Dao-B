package com.haui.demo.models.requests;


import com.haui.demo.models.responses.ImageRp;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

public class BuildingRq {

    @NotBlank
    private String id;

    @NotBlank
    private String name;

    @NotBlank
    private String description;

    @NotBlank
    private String title;

    @NotNull
    private Integer carPark;

    @NotNull
    private Integer motoPark;

    @NotNull
    private Integer floorArea;

    @NotNull
    private Integer homeFrontage;

    @NotNull
    private Integer numberFloor;

    @NotNull
    private Integer bedroom;

    @NotNull
    private Integer functionRoom;

    @NotNull
    private Integer altarRoom;

    @NotNull
    private Long price;

    @NotNull
    private Integer campusArea;

    @NotBlank
    private String direction;

    @NotBlank
    private String map;

    @NotNull
    private Integer electricityPrice;

    @NotNull
    private Integer frequence;

    @NotNull
    private Integer waterPrice;

    @NotNull
    private Integer servicePrice;

    @NotNull
    private Integer homeDeposit;

    @NotBlank
    private String address;

    @NotNull
    private Integer ward;

    @NotBlank
    private String buildingCategory;

    @NotNull
    private Integer saleRent;

    @NotBlank
    private List<ImageRq> images = new ArrayList<>();

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getCarPark() {
        return carPark;
    }

    public void setCarPark(Integer carPark) {
        this.carPark = carPark;
    }

    public Integer getMotoPark() {
        return motoPark;
    }

    public void setMotoPark(Integer motoPark) {
        this.motoPark = motoPark;
    }

    public Integer getFloorArea() {
        return floorArea;
    }

    public void setFloorArea(Integer floorArea) {
        this.floorArea = floorArea;
    }

    public Integer getHomeFrontage() {
        return homeFrontage;
    }

    public void setHomeFrontage(Integer homeFrontage) {
        this.homeFrontage = homeFrontage;
    }

    public Integer getNumberFloor() {
        return numberFloor;
    }

    public void setNumberFloor(Integer numberFloor) {
        this.numberFloor = numberFloor;
    }

    public Integer getBedroom() {
        return bedroom;
    }

    public void setBedroom(Integer bedroom) {
        this.bedroom = bedroom;
    }

    public Integer getFunctionRoom() {
        return functionRoom;
    }

    public void setFunctionRoom(Integer functionRoom) {
        this.functionRoom = functionRoom;
    }

    public Integer getAltarRoom() {
        return altarRoom;
    }

    public void setAltarRoom(Integer altarRoom) {
        this.altarRoom = altarRoom;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Integer getCampusArea() {
        return campusArea;
    }

    public void setCampusArea(Integer campusArea) {
        this.campusArea = campusArea;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getMap() {
        return map;
    }

    public void setMap(String map) {
        this.map = map;
    }

    public Integer getElectricityPrice() {
        return electricityPrice;
    }

    public void setElectricityPrice(Integer electricityPrice) {
        this.electricityPrice = electricityPrice;
    }

    public Integer getFrequence() {
        return frequence;
    }

    public void setFrequence(Integer frequence) {
        this.frequence = frequence;
    }

    public Integer getWaterPrice() {
        return waterPrice;
    }

    public void setWaterPrice(Integer waterPrice) {
        this.waterPrice = waterPrice;
    }

    public Integer getServicePrice() {
        return servicePrice;
    }

    public void setServicePrice(Integer servicePrice) {
        this.servicePrice = servicePrice;
    }

    public Integer getHomeDeposit() {
        return homeDeposit;
    }

    public void setHomeDeposit(Integer homeDeposit) {
        this.homeDeposit = homeDeposit;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getWard() {
        return ward;
    }

    public void setWard(Integer ward) {
        this.ward = ward;
    }

    public String getBuildingCategory() {
        return buildingCategory;
    }

    public void setBuildingCategory(String buildingCategory) {
        this.buildingCategory = buildingCategory;
    }

    public Integer getSaleRent() {
        return saleRent;
    }

    public void setSaleRent(Integer saleRent) {
        this.saleRent = saleRent;
    }

    public List<ImageRq> getImages() {
        return images;
    }

    public void setImages(List<ImageRq> images) {
        this.images = images;
    }
}
