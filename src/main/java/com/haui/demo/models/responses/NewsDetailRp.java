package com.haui.demo.models.responses;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

public class NewsDetailRp {
    private String id;

    private String name;

    private String title;

    private String content;

    private String newsCategory;

    private List<ImageRp> imageRqs = new ArrayList<>();

    private ZonedDateTime createdAt;

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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getNewsCategory() {
        return newsCategory;
    }

    public void setNewsCategory(String newsCategory) {
        this.newsCategory = newsCategory;
    }

    public List<ImageRp> getImageRqs() {
        return imageRqs;
    }

    public void setImageRqs(List<ImageRp> imageRqs) {
        this.imageRqs = imageRqs;
    }

    public ZonedDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(ZonedDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
