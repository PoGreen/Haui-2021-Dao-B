package com.haui.demo.models.responses;

public class BuildingRp {
    private String id;
    private String name;
    private String description;
    private String address;
    private Integer bedroom;
    private Integer functionRoom;
    private Integer price;
    private Integer floorArea;

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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getFloorArea() {
        return floorArea;
    }

    public void setFloorArea(Integer floorArea) {
        this.floorArea = floorArea;
    }
}
