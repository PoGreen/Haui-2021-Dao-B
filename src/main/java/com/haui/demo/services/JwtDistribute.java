package com.haui.demo.services;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.haui.demo.models.entities.User;
import com.haui.demo.utils.Global;
import com.haui.demo.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class JwtDistribute {
    @Autowired
    private JwtUser jwtUser;

    public DecodedJWT authenticate(HttpServletRequest request, HttpServletResponse response) throws Exception {

        JwtService jwtService = getJwtObject(request);
        if (jwtService == null) return null;
        return jwtService.authenticate(request, response);

    }

    public String findNGenerateToken(Object o) {

        if (o instanceof User)
            return jwtUser.findNGenerateToken((User)o);
        return "";
    }

    /**
     * Get Claims in token
     *
     * @param request
     * @return
     * @throws Exception
     */
    public Object getClaims(HttpServletRequest request) {

        JwtService jwtService = getJwtObject(request);
        if (jwtService == null)
            return false;

        return jwtService.getClaims(request);
    }


    /**
     *
     * @param request
     * @return
     * @throws Exception
     */
    private JwtService getJwtObject(HttpServletRequest request) {
        String token = request.getHeader(Global.AUTHORIZATION);
        if (Utils.notNullAndEmpty(token))
            return jwtUser;

        return null;
    }


}
