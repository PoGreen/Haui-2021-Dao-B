package com.haui.demo.models.responses;

public class BuildingRp {
    private String id;
    private String name;
    private String description;
    private String address;
    private Integer bedroom;
    private Integer functionRoom;
    private Long price;
    private String title;
    private Integer floorArea;
    private Integer saleRent;
    private Integer status;
    private String categoryName;
    private String buildingCategoryId;

    private ImageRp imageRp;

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

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Integer getFloorArea() {
        return floorArea;
    }

    public void setFloorArea(Integer floorArea) {
        this.floorArea = floorArea;
    }

    public ImageRp getImageRp() {
        return imageRp;
    }

    public void setImageRp(ImageRp imageRp) {
        this.imageRp = imageRp;
    }

    public Integer getSaleRent() {
        return saleRent;
    }

    public void setSaleRent(Integer saleRent) {
        this.saleRent = saleRent;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getBuildingCategoryId() {
        return buildingCategoryId;
    }

    public void setBuildingCategoryId(String buildingCategoryId) {
        this.buildingCategoryId = buildingCategoryId;
    }
}
