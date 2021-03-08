package com.haui.demo.models.entities;

import javax.persistence.Column;
import javax.persistence.Entity;


@Entity(name = "buildings")
public class Building extends AbsEntity {
    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "car_pard")
    private Integer carPark;

    @Column(name = "moto_park")
    private Integer motoPark;

    @Column(name = "floor_area")
    private Integer floorArea;

    @Column(name = "home_frontage")
    private Integer homeFrontage;

    @Column(name = "number_floor")
    private Integer numberFloor;

    @Column(name = "bedroom")
    private Integer bedroom;

    @Column(name = "function_room")
    private Integer functionRoom;

    @Column(name = "altar_room")
    private Integer altarRoom;

    @Column(name = "campus_area")
    private Integer campusArea;

    @Column(name = "direction")
    private String direction;

    @Column(name = "map")
    private String map;

    @Column(name = "electricity_price")
    private Integer electricityPrice;

    @Column(name = "frequence")
    private Integer frequence;

    @Column(name = "water_price")
    private Integer waterPrice;

    @Column(name = "service_price")
    private Integer servicePrice;

    @Column(name = "home_depsist")
    private Integer homeDeposit;

    @Column(name = "address")
    private String address;

    @Column(name = "ward")
    private Integer ward;

    @Column(name = "building_category")
    private String buildingCategory;

    @Column(name = "user")
    private String user;

    @Column(name = "sale_rent")
    private Integer saleRent;

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

    public void setWard(Integer ward) {
        this.ward = ward;
    }

    public String getBuildingCategory() {
        return buildingCategory;
    }

    public void setBuildingCategory(String buildingCategory) {
        this.buildingCategory = buildingCategory;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public Integer getSaleRent() {
        return saleRent;
    }

    public void setSaleRent(Integer saleRent) {
        this.saleRent = saleRent;
    }
}
