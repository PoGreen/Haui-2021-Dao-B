package com.haui.demo.models.responses;

import com.haui.demo.models.requests.ImageRq;

import java.util.ArrayList;
import java.util.List;

public class NewsRp {
    private String id;

    private String name;

    private String title;

    private String newsCategory;

    private List<ImageRp> imageRqs = new ArrayList<>();

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
}
