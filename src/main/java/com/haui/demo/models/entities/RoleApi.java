package com.haui.demo.models.entities;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class RoleApi extends AbsEntity{

    @Column(name = "role_id")
    private String role;

    @Column(name = "api_id")
    private String api;

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getApi() {
        return api;
    }

    public void setApi(String api) {
        this.api = api;
    }
}
