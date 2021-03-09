package com.haui.demo.models.entities;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity(name = "images")
public class Image extends AbsEntity {

    @Column(name = "name")
    private String name;

    @Column(name = "type")
    private String type;

    @Column(name = "buildings")
    private String building;

    @Column(name = "news")
    private String news;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public String getNews() {
        return news;
    }

    public void setNews(String news) {
        this.news = news;
    }
}
