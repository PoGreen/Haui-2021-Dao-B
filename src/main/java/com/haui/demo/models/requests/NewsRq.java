package com.haui.demo.models.requests;

import javax.persistence.Column;
import java.util.ArrayList;
import java.util.List;

public class NewsRq {

    private String id;

    private String name;

    private String title;

    private String content;

    private String newsCategory;

    private List<ImageRq> images = new ArrayList<>();

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

    public List<ImageRq> getImages() {
        return images;
    }

    public void setImages(List<ImageRq> images) {
        this.images = images;
    }
}
