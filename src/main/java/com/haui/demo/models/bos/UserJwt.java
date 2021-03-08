package com.haui.demo.models.bos;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.haui.demo.models.entities.User;
import com.haui.demo.utils.Global;


public class UserJwt {

    private String id;
    private String role;
    private DecodedJWT decodedJWT;

    public UserJwt() {
    }

    public UserJwt(String id, String role) {
        this.id = id;
        this.role = role;
    }

    public static UserJwt from(DecodedJWT decodedJWT) {
        return new UserJwt(decodedJWT.getClaim("id").asString(), decodedJWT.getClaim("role").asString());
    }

    public static UserJwt from(User user) {
        return new UserJwt(user.getId(),user.getRole());
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public DecodedJWT getDecodedJWT() {
        return decodedJWT;
    }

    public void setDecodedJWT(DecodedJWT decodedJWT) {
        this.decodedJWT = decodedJWT;
    }

    @Override
    public String toString() {
        return Global.mapToString(this);
    }
}
