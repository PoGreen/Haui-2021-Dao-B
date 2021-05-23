package com.haui.demo.models.requests;


public class BuildingFilter extends AbsFilter {
    private int floorArea;
    private int bedRoom;
    private int functionRoom;
    private long price;
    private String direction;
    private Integer ward;
    private int saleRent;
    private String buildingCategory;
    private String user;
    private int status;

    public int getFloorArea() {
        return floorArea;
    }

    public void setFloorArea(Integer floorArea) {
        this.floorArea = floorArea != null ? floorArea : 0;
    }

    public int getBedRoom() {
        return bedRoom;
    }

    public void setBedRoom(Integer bedRoom) {
        this.bedRoom = bedRoom != null ? bedRoom : 0;
    }

    public int getFunctionRoom() {
        return functionRoom;
    }

    public void setFunctionRoom(Integer functionRoom) {
        this.functionRoom = functionRoom != null ? functionRoom : 0;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price != null ? price : 0;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public Integer getWard() {
        return ward;
    }

    public void setWard(Integer ward) {
        this.ward = ward != null ? ward : 0;
    }

    public int getSaleRent() {
        return saleRent;
    }

    public void setSaleRent(Integer saleRent) {
        this.saleRent = saleRent != null ? saleRent : 0;
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

    public int getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status != null ? status : 0;
    }


}
