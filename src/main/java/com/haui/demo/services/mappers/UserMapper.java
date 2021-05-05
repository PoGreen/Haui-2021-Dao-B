package com.haui.demo.services.mappers;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import com.haui.demo.models.bos.SystemResponse;
import com.haui.demo.models.entities.Building;
import com.haui.demo.models.entities.User;
import com.haui.demo.models.requests.AccountUpdateRq;
import com.haui.demo.models.requests.AdminRq;
import com.haui.demo.models.requests.SignupRq;
import com.haui.demo.models.requests.UserRequest;
import com.haui.demo.models.responses.BuildingRp;
import com.haui.demo.models.responses.UserRp;
import com.haui.demo.models.responses.SignupRp;
import com.haui.demo.models.responses.UserLoginResponse;
import com.haui.demo.repositories.RoleRepository;
import com.haui.demo.repositories.UserRepository;
import com.haui.demo.services.ILocationService;
import com.haui.demo.services.impls.FirebaseService;
import com.haui.demo.utils.Global;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class UserMapper {

    @Autowired
    private ILocationService locationService;

    @Autowired
    private RoleRepository roleRepository;

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
        user.setPhone(signupRq.getPhone());
        user.setAddress(signupRq.getAddress());
        user.setFullName(signupRq.getFullName());
        user.setEmail(signupRq.getEmail());
        user.setWard(ward);

        return user;
    }

    public User map(User user, AccountUpdateRq accountUpdateRq) {

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

    public UserRp mapRp(User user) {
        UserRp userRp = new UserRp();
        userRp.setId(user.getId());
        userRp.setUserName(user.getUserName());
        userRp.setFullName(user.getFullName());
        userRp.setAddress(user.getPassword());
        userRp.setPhone(user.getPhone());
        userRp.setEmail(user.getEmail());
        userRp.setStatus(user.getStatus());
        userRp.setCreatedAt(user.getCreatedAt());
        userRp.setAddress(locationService.getLocationByWard(user.getWard()) + user.getAddress());
        userRp.setRoleName(roleRepository.findById(user.getRole()).get().getRoleName());
        return userRp;
    }

    public List<UserRp> mapRps(List<User> users){
        return users.stream().map(this::mapRp).collect(Collectors.toList());
    }

    public Page<UserRp> map(Page<User> users) {
        return users.map(this::mapRp);
    }
}
