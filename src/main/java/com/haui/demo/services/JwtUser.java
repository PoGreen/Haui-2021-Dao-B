package com.haui.demo.services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.haui.demo.models.bos.UserJwt;
import com.haui.demo.models.entities.User;
import com.haui.demo.repositories.UserRepository;
import com.haui.demo.utils.Global;
import com.haui.demo.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.Duration;
import java.time.Instant;
import java.util.Collection;
import java.util.Date;

@Service
public class JwtUser extends JwtService {
    private final Algorithm algorithm;
    private final JWTVerifier jwtVerifier;
    @Autowired
    private UserRepository userRepository;

    // Hour
    @Value("${app.jwt.ttl}")
    private Duration ttlJwt;

    @Autowired
    public JwtUser(@Value("${app.jwt.secret}") String secretJwt) {
        this.algorithm = Algorithm.HMAC256(secretJwt);
        this.jwtVerifier = JWT.require(this.algorithm).build();
    }

    @Override
    public DecodedJWT authenticate(HttpServletRequest request, HttpServletResponse response) {

        String token = getJWTToken(request);
        if (token.isEmpty()) {
            return null;
        }

        DecodedJWT decodedJWT = validateJWT(token);
        if (decodedJWT == null) {
            return null;
        }

        request.setAttribute(Global.USER_ATTR, decodedJWT);

        setToken(getID(request), token);

        return decodedJWT;
    }

    /**
     * @param request
     * @return
     */
    public String getJWTToken(HttpServletRequest request) {
        String authorizationVal = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (authorizationVal == null
                || authorizationVal.length() <= Global.BEARER.length() + 1
                || !authorizationVal.startsWith(Global.BEARER))
            return "";

        return authorizationVal.substring(Global.BEARER.length() + 1);
    }

    public DecodedJWT validateJWT(String token) {
        DecodedJWT decodedJWT;
        try {
            decodedJWT = this.jwtVerifier.verify(token);
        } catch (Exception e) {
            return null;
        }
        try (Jedis jedis = jedisPool.getResource()) {
            String redisKey = toToken(UserJwt.from(decodedJWT).getId(), token);
            if (!jedis.exists(redisKey))
                return null;

            return decodedJWT;
        }
    }

    /**
     * @param request
     * @return
     */
    public Object getClaims(HttpServletRequest request) {
        DecodedJWT decodedJWT;
        try {
            decodedJWT = (DecodedJWT) request.getAttribute(Global.USER_ATTR);
        } catch (Exception e) {
            return null;
        }
        if (decodedJWT == null)
            return null;

        return UserJwt.from(decodedJWT);
    }

    public void revokeToken(String id) {
        try (Jedis jedis = jedisPool.getResource()) {
            Collection<String> tokens = jedis.keys(id + Global.SEPARATOR + "*");
            if (tokens.isEmpty()) {
                return;
            }
            jedis.del(tokens.toArray(new String[0]));
        }
    }

    @Override
    public String findToken(String accountId) {
        try (Jedis jedis = jedisPool.getResource()) {
            Collection<String> tokens = jedis.keys(accountId + Global.SEPARATOR + "*");
            if (tokens.isEmpty()) {
                return "";
            }
            return tokens.iterator().next().replaceFirst(accountId + Global.SEPARATOR, "");
        }
    }

    /**
     * @param request
     * @return
     */
    public String getID(HttpServletRequest request) {
        UserJwt credentialJwt = (UserJwt) getClaims(request);

        if (credentialJwt == null)
            return "";

        return credentialJwt.getId();
    }
    public User getUser(HttpServletRequest request) {
        UserJwt credentialJwt = (UserJwt) getClaims(request);

        if (credentialJwt == null)
            return null;

        return userRepository.findById(credentialJwt.getId()).orElse(null);
    }
    public String generateJWT(UserJwt accountJwt) {
        String token = JWT.create()
                .withIssuedAt(Date.from(Instant.now()))
                .withClaim("id", accountJwt.getId())
                .withClaim("role", accountJwt.getRole())
                .sign(this.algorithm);

        // set token
        setToken(accountJwt.getId(), token);

        return token;
    }


    public void setToken(String id, String token) {
        try (Jedis jedis = jedisPool.getResource()) {
            String key = toToken(id, token);
            jedis.set(key, "1");
            jedis.expire(key, (int) ttlJwt.getSeconds());
        }
    }

    public String findNGenerateToken(User user) {
        try (Jedis jedis = jedisPool.getResource()) {
            Collection<String> tokens = jedis.keys(user.getId() + Global.SEPARATOR + "*");
            if (Utils.notNullAndEmpty(tokens)) {
                jedis.del(tokens.toArray(new String[0]));
            }
        }
        return generateJWT(UserJwt.from(user));
    }

}
