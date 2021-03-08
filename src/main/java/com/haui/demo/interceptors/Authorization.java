package com.haui.demo.interceptors;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.haui.demo.models.bos.UserJwt;
import com.haui.demo.models.entities.RoleApi;
import com.haui.demo.repositories.ApiRepository;
import com.haui.demo.repositories.RoleApiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

@Component
public class Authorization {

    @Autowired
    private RoleApiRepository roleApiRepository;

    @Autowired
    private ApiRepository apiRepository;

    public boolean verifyRole(DecodedJWT decodedJWT, HttpServletRequest request) {
        String uri = request.getRequestURI();
        UserJwt userJwt = UserJwt.from(decodedJWT);

        RoleApi roleApi = roleApiRepository.existsByRoleAndApiUrl(uri,userJwt.getRole());

        if(Objects.isNull(roleApi)) return false;
        return true;
    }
}
