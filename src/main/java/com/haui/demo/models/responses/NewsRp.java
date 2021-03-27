package com.haui.demo.models.responses;


import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

public class NewsRp {
    private String id;

    private String nameNews;

    private String title;

    private String newsCategory;

    private ImageRp imageRp;

    private ZonedDateTime createdAt;

    private List<ImageRp> imageRqs = new ArrayList<>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNameNews() {
        return nameNews;
    }

    public void setNameNews(String nameNews) {
        this.nameNews = nameNews;
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

    public ImageRp getImageRp() {
        return imageRp;
    }

    public void setImageRp(ImageRp imageRp) {
        this.imageRp = imageRp;
    }

    public ZonedDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(ZonedDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
