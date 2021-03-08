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
            API.with("^/users/login$"),
            API.with("^/users/otps$"),
            API.with("^/users/otps/verifier$"),
            API.with("^/images/(.*)$"),
            API.with("^/images$"),
            API.with("^/options$"),
            API.with("^/v2/api-docs(.*)$"),
            API.with("^(.*)swagger(.*)$"),
            API.with("^/$"),
            API.with("^/csrf$"),
            API.with("^/actuator(.*)$"),
            API.with("^/favicon\\.ico$"),
            API.with("^/swagger-ui/index.html$")
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


        if (isSkipAuthAPI(request)) return true;

        DecodedJWT decodedJWT = jwt.authenticate(request, response);

        if (decodedJWT == null) {
            ResponseEntity<SystemResponse<Object>> userResponse = firebaseService.verifyToken(request);
            if (userResponse.getStatusCode().is2xxSuccessful()) {
                User user = (User) Objects.requireNonNull(userResponse.getBody()).getData();
                if (user != null) {
                    request.setAttribute(Global.USER_ATTR, user);
                    jwt.findNGenerateToken(user);
                    return true;
                }
            }
            SystemResponse<?> errorResponse = new SystemResponse<>(HttpStatus.UNAUTHORIZED.value(), "UNAUTHORIZED!");
            response.setStatus(StatusResponse.UNAUTHORIZED);
            response.setContentType("application/json");
            response.getWriter().println(errorResponse.toString());
            logger.error(StringResponse.UNAUTHORIZED);
            return false;
        }

        request.setAttribute(Global.USER_ATTR, decodedJWT);
        UserJwt user = UserJwt.from(decodedJWT);
        jwt.findNGenerateToken(user);
//            boolean author = authorization.verifyRole(decodedJWT, request);
//        if (!author) {
//            SystemResponse<?> errorResponse = new SystemResponse<>(HttpStatus.UNAUTHORIZED.value(), "UNAUTHORIZED!");
//            response.setStatus(StatusResponse.UNAUTHORIZED);
//            response.setContentType("application/json");
//            response.getWriter().println(errorResponse.toString());
//            logger.error(StringResponse.UNAUTHORIZED);
//            return true;
//        }
        return true;
    }

    private boolean isSkipAuthAPI(HttpServletRequest request) {
        for (API skipAuthAPI : this.skipAuthAPIs) {
            if (skipAuthAPI.isSkipRequest(request)) return true;
        }
        return false;
    }
}
