package com.haui.demo.services.mappers;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import com.haui.demo.models.bos.SystemResponse;
import com.haui.demo.models.entities.User;
import com.haui.demo.models.requests.AccountUpdateRq;
import com.haui.demo.models.requests.AdminRq;
import com.haui.demo.models.requests.SignupRq;
import com.haui.demo.models.requests.UserRequest;
import com.haui.demo.models.responses.SignupRp;
import com.haui.demo.models.responses.UserLoginResponse;
import com.haui.demo.repositories.UserRepository;
import com.haui.demo.services.impls.FirebaseService;
import com.haui.demo.utils.Global;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

@Component
public class UserMapper {

    private static final Logger logger = LogManager.getLogger(UserMapper.class);
    private final FirebaseService firebaseService;
    private final UserRepository userRepository;

    public UserMapper(FirebaseService firebaseService, UserRepository userRepository) {
        this.firebaseService = firebaseService;
        this.userRepository = userRepository;
    }

    public User map(UserRequest request) {
        User userExist = null;
        userExist.setPhone(request.getPhone());
        return userExist;
    }

    public UserLoginResponse map(HttpServletRequest request, UserRequest userRequest) {
        ResponseEntity<SystemResponse<Object>> tokenResponse = firebaseService.genTokenFromFirebase(request);
        if (!tokenResponse.getStatusCode().is2xxSuccessful()) {
            return null;
        }
        String accessToken = Global.objectMapper.convertValue(Objects.requireNonNull(tokenResponse.getBody()).getData(), String.class);
        FirebaseToken auth;
        try {
            auth = FirebaseAuth.getInstance().verifySessionCookie(accessToken);
        } catch (FirebaseAuthException e) {
            logger.error(e);
            return null;
        }
//        User userExist = userRepository.findByUserId(auth.getUid());
        User userExist = null;
        return new UserLoginResponse(userExist, accessToken);
    }

    public User map(SignupRq signupRq, Integer ward) {
        User user = new User();
        user.setUserName(signupRq.getUserName());
        user.setPassword(signupRq.getPassword());
        user.setPhone(signupRq.getPhone());
        user.setAddress(signupRq.getAddress());
        user.setFullName(signupRq.getFullName());
        user.setEmail(signupRq.getEmail());
        user.setWard(ward);

        return user;
    }

    public User map(User user, AccountUpdateRq accountUpdateRq ) {
        user.setPassword(accountUpdateRq.getPassword());
        user.setPhone(accountUpdateRq.getPhone());
        user.setAddress(accountUpdateRq.getAddress());
        user.setFullName(accountUpdateRq.getFullName());
        user.setEmail(accountUpdateRq.getEmail());
        user.setWard(accountUpdateRq.getWard());

        return user;
    }

    public User map(AdminRq adminRq, Integer ward) {
        User user = new User();
        user.setUserName(adminRq.getUserName());
        user.setPassword(adminRq.getPassword());
        user.setPhone(adminRq.getPhone());
        user.setAddress(adminRq.getAddress());
        user.setFullName(adminRq.getFullName());
        user.setEmail(adminRq.getEmail());
        user.setWard(ward);

        return user;
    }

    public SignupRp map(User user) {

        SignupRp signupRp = new SignupRp();
        signupRp.setUserName(user.getUserName());
        signupRp.setPassword(user.getPassword());
        signupRp.setId(user.getId());

        return signupRp;
    }
}
