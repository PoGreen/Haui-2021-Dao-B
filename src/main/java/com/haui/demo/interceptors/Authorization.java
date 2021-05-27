package com.haui.demo.interceptors;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.haui.demo.models.bos.UserJwt;
import com.haui.demo.models.entities.RoleApi;
import com.haui.demo.repositories.ApiRepository;
import com.haui.demo.repositories.RoleApiRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

@Component
public class Authorization {
    private static final Logger logger = LogManager.getLogger(Authorization.class);

    @Autowired
    private RoleApiRepository roleApiRepository;

    @Autowired
    private ApiRepository apiRepository;

    public boolean verifyRole(DecodedJWT decodedJWT, HttpServletRequest request) {
        logger.info("===========================================Start verify role");
        String uri = request.getRequestURI();
        String method = request.getMethod();

        UserJwt userJwt = UserJwt.from(decodedJWT);

        RoleApi roleApi = roleApiRepository.existsByRoleAndApiUrl(uri, userJwt.getRole(), method);

        if (Objects.isNull(roleApi)) return false;
        return true;
    }
}
