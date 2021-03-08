package com.haui.demo.services.impls;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import com.google.firebase.auth.SessionCookieOptions;
import com.haui.demo.models.bos.Response;
import com.haui.demo.models.bos.SystemResponse;
import com.haui.demo.repositories.UserRepository;
import com.haui.demo.utils.Global;
import com.haui.demo.utils.StringResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.util.Strings;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.TimeUnit;

@Service
public class FirebaseService {
    private static final Logger logger = LogManager.getLogger(FirebaseService.class);

    private final UserRepository userRepository;

    public FirebaseService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public ResponseEntity<SystemResponse<Object>> genTokenFromFirebase(HttpServletRequest request) {
        String tokenHeader = getTokenHeader(request);
        if (Strings.isEmpty(tokenHeader)) {
            return Response.unauthorized(StringResponse.UNAUTHORIZED);
        }
        logger.info("token request: " + tokenHeader);
        String accessToken;
        try {
            // expire token for client from idToken provider
            long expiresIn = TimeUnit.DAYS.toMillis(13);
            SessionCookieOptions options = SessionCookieOptions.builder()
                    .setExpiresIn(expiresIn)
                    .build();
            accessToken = FirebaseAuth.getInstance().createSessionCookie(tokenHeader, options);
            logger.info("token response: " + accessToken);
        } catch (FirebaseAuthException e) {
            logger.error("authenticate :" + e);
            return Response.unauthorized(StringResponse.INVALID_TOKEN);
        }
        return Response.ok(StringResponse.SUCCESS, accessToken);
    }

    public ResponseEntity<SystemResponse<Object>> verifyToken(HttpServletRequest request) {
        String token = getTokenHeader(request);
        if (Strings.isEmpty(token)) {
            return Response.unauthorized(StringResponse.UNAUTHORIZED);
        }
        if (Strings.isEmpty(token)) {
            return Response.unauthorized();
        }
        try {
            FirebaseToken firebaseToken = FirebaseAuth.getInstance().verifySessionCookie(token, true);
//            return Response.ok(userRepository.findByUserId(firebaseToken.getUid()));
            return Response.unauthorized();

        } catch (FirebaseAuthException e) {
            return Response.unauthorized();
        }
    }

    public String genTokenFromFirebase(String tokenFB) {
        String accessToken;
        try {
            // expire token for client from idToken provider
            long expiresIn = TimeUnit.DAYS.toMillis(13);
            SessionCookieOptions options = SessionCookieOptions.builder()
                    .setExpiresIn(expiresIn)
                    .build();
            accessToken = FirebaseAuth.getInstance().createSessionCookie(tokenFB, options);
            logger.info("token response: " + accessToken);
        } catch (FirebaseAuthException e) {
            logger.error("authenticate :" + e);
            return null;
        }
        return accessToken;
    }

    public String getTokenHeader(HttpServletRequest request) {
        String authorizationVal = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (authorizationVal == null ||
                authorizationVal.length() <= Global.BEARER.length() + 1 ||
                !authorizationVal.startsWith(Global.BEARER)) {
            return null;
        }
        return authorizationVal.substring(Global.BEARER.length() + 1);
    }
}
