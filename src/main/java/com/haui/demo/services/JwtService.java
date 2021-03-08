package com.haui.demo.services;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.haui.demo.utils.Global;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.JedisPool;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public abstract class JwtService {

    @Autowired
    protected JedisPool jedisPool;

    public abstract DecodedJWT authenticate(HttpServletRequest request, HttpServletResponse response) throws Exception;

    public abstract Object getClaims(HttpServletRequest request);

//    public abstract void revokeToken(String accountId, String token);

    public abstract String findToken(String accountId);

    public abstract DecodedJWT validateJWT(String token);

//    public abstract String generateJWT(AccountJwt accountJwt);

    public String toToken(String accountId, String token) {
        return accountId + Global.SEPARATOR + token;
    }

}
