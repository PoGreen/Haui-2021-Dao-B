package com.haui.demo.models.requests;

public class NewsCategoryRq {

    private String id;

    private String name;

    private String description;

    private String newscategory;

    private Integer status;

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

    public String getNewscategory() {
        return newscategory;
    }

    public void setNewscategory(String newscategory) {
        this.newscategory = newscategory;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
