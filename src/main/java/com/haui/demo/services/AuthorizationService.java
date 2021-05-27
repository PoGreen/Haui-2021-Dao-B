package com.haui.demo.services;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.haui.demo.interceptors.AuthInterceptor;
import com.haui.demo.interceptors.Authorization;
import com.haui.demo.models.bos.SystemResponse;
import com.haui.demo.models.entities.Api;
import com.haui.demo.models.entities.Role;
import com.haui.demo.models.entities.RoleApi;
import com.haui.demo.models.entities.User;
import com.haui.demo.repositories.ApiRepository;
import com.haui.demo.repositories.RoleApiRepository;
import com.haui.demo.repositories.UserRepository;
import com.haui.demo.utils.Global;
import com.haui.demo.utils.StatusResponse;
import com.haui.demo.utils.StringResponse;
import com.haui.demo.utils.Utils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Service
public class AuthorizationService {

    @Autowired
    private JwtDistribute jwt;

    @Autowired
    private Authorization authorization;

    @Autowired
    private RoleApiRepository roleApiRepository;

    @Autowired
    private ApiRepository apiRepository;

    @Autowired
    private UserRepository userRepository;

    private static final Logger logger = LogManager.getLogger(AuthInterceptor.class);

    public boolean verify(HttpServletRequest request, HttpServletResponse response, String url, String method) throws Exception {
        String token = jwt.getJWTToken(request);
        logger.info("Token ==========================================================================================>{}" + token);
        if (token.isEmpty()) {
            response.sendRedirect("/login");
            return false;
        }

        request.setAttribute(Global.AUTHORIZATION, token);
        DecodedJWT decodedJWT = jwt.authenticate(request, response);
        if (decodedJWT == null) {
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.getWriter().println("invalid or expired access token");
            response.sendRedirect("/login");
            return false;
        }

        request.setAttribute(Global.USER_ATTR, decodedJWT);


        User user = userRepository.findById(decodedJWT.getClaim("id").asString()).orElse(null);
        if (user == null) return false;
        Api api = apiRepository.findByUrlAndMethod(url, method);
        if (api == null) return false;
        return roleApiRepository.existsByApiAndRole(api.getId(), user.getRole());
    }

}
