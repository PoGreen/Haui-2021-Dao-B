package com.haui.demo.models.entities;

import javax.persistence.Column;
import javax.persistence.Entity;


@Entity(name="news")
public class News extends AbsEntity{

    @Column(name="name")
    private String name;

    @Column(name="title")
    private String title;

    @Column(name="content", columnDefinition = "TEXT")
    private String content;

    @Column(name = "news_category")
    private String newsCategory;

    @Column(name = "user")
    private String user;


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

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}
