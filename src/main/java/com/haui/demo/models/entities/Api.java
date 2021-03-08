package com.haui.demo.models.entities;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity(name = "apis")
public class Api extends AbsEntity{

    @Column(name = "url")
    public String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
