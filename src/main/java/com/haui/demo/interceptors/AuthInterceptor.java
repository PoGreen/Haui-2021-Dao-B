package com.haui.demo.interceptors;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.haui.demo.models.bos.API;
import com.haui.demo.models.bos.SystemResponse;
import com.haui.demo.models.bos.UserJwt;
import com.haui.demo.models.entities.User;
import com.haui.demo.services.JwtDistribute;
import com.haui.demo.services.impls.FirebaseService;
import com.haui.demo.utils.Global;
import com.haui.demo.utils.StatusResponse;
import com.haui.demo.utils.StringResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

@Component
public class AuthInterceptor implements HandlerInterceptor {

    private final Authorization authorization;

    private static final Logger logger = LogManager.getLogger(AuthInterceptor.class);
    private final JwtDistribute jwt;
    private final FirebaseService firebaseService;
    private final API[] skipAuthAPIs = new API[]{
            API.with("^/login$"),
            API.with("^/news"),
            API.with("^/users/login$"),
            API.with("^/users/otps$"),
            API.with("^/users/otps/verifier$"),
            API.with("^/images/(.*)$"),
            API.with("^/images$"),
            API.with("^/home"),
            API.with("^/buildings-detail"),
            API.with("^/options$"),
            API.with("^/v2/api-docs(.*)$"),
            API.with("^/buildings-list-page$"),
            API.with("^/$"),
            API.with("^/csrf$"),
            API.with("^/signup$"),
            API.with("^/favicon\\.ico$"),
            API.with("^/verify-role$"),
            API.with("^(.*)css$"),
            API.with("^(.*)/js$"),
            API.with("(.*)png$"),
            API.with("(.*)jpg$"),
            API.with("(.*)jpeg$"),
            API.with("^(/*)provinces$"),
            API.with("^(/*)districts"),
            API.with("(/*)wards"),
            API.with("^/buildings/(.*)$"),
            API.with("^/locations(.*)$"),
            API.with("^/fonts"),
            API.with("^/building-categories"),
            API.with("^/admin/(.*)$"),
            API.with("^/buildings-page$"),
            API.with("^/css/(.*)$"),
            API.with("^/js/(.*)$"),
            API.with("^/fonts/(.*)$"),
            API.with("^/img/(.*)$"),
            API.with("^/locations/provinces$"),
            API.with("^/locations/(.*)$"),
            API.with("^/ckeditor/(.*)$"),


            API.with("^/buildings-detail$"),
            API.with("^/buildings-rent-page$"),
            API.with("^/buildings-buy-page$"),
            API.with("^/news-page$"),
            API.with("^/news-page-detail$"),
            API.with("^/forgot/password"),
            API.with("^/news/status$"),
            API.with("^/news$"),
            API.with("^/news$/(.*)$"),
            API.with("^/news/categories$"),
            API.with("^/buildings$"),
            API.with("^/news-categories$"),
            API.with("^/rss-news$"),
            API.with("^/users/signup$"),
            API.with("^/admin/new-buildings$"),
            API.with("^/users/logout$"),


            API.with("^/admin/new-buildings-categories$"),
            API.with("^/admin/buildings-categories$"),
            API.with("^/admin/new-buildings$"),
            API.with("^/admin/buildings$"),
            API.with("^/buildings-edit$"),
            API.with("^/admin/home$"),
            API.with("^/admin/new-news-categories$"),
            API.with("^/admin/news-categories$"),
            API.with("^/admin/news$"),
            API.with("^/add-news-page$"),
            API.with("^/news-page-edit$"),
            API.with("^/admin/list-users-page$"),

            API.with("^/edit-info$"),
            API.with("^/users/logout$"),
            API.with("^/users/logout$"),
            API.with("^/users/logout$"),
            API.with("^/users/logout$"),
            API.with("^/users/logout$"),
            API.with("^/users/logout$"),
            API.with("^/users/logout$"),
            API.with("^/users/logout$"),
            API.with("^/users/logout$"),
            API.with("^/users/logout$")
    };

    @Autowired
    public AuthInterceptor(JwtDistribute jwt, FirebaseService firebaseService, Authorization authorization) {
        this.jwt = jwt;
        this.firebaseService = firebaseService;
        this.authorization = authorization;
    }

    /**
     * handler token from header request
     * check in redis with token. decode
     *
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        logger.info("start preHandle =>>>>>>>>>>>>>>>>{}");
        logger.info("Uri =>>>>>>>>>>>>>>>>{}" + request.getRequestURI());

        if (isSkipAuthAPI(request)) return true;

        String token = jwt.getJWTToken(request);
        if (token.isEmpty() || token.equals("")) {
            response.sendRedirect("/login");
            return false;
        }

        DecodedJWT decodedJWT = jwt.authenticate(request, response);
        if (decodedJWT == null) {
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.getWriter().println("invalid or expired access token");
            response.sendRedirect("/login");
            return false;
        }

        request.setAttribute(Global.USER_ATTR, decodedJWT);
        return true;
    }

    private boolean isSkipAuthAPI(HttpServletRequest request) {
        for (API skipAuthAPI : this.skipAuthAPIs) {
            if (skipAuthAPI.isSkipRequest(request)) return true;
        }
        return false;
    }
}
