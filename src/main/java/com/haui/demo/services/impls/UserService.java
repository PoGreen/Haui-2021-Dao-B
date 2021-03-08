package com.haui.demo.services.impls;

import com.haui.demo.models.bos.Response;
import com.haui.demo.models.bos.SystemResponse;
import com.haui.demo.models.entities.User;
import com.haui.demo.models.requests.Login;
import com.haui.demo.models.responses.UserLoginResponse;
import com.haui.demo.repositories.UserRepository;
import com.haui.demo.services.IUserService;
import com.haui.demo.services.JwtDistribute;
import com.haui.demo.services.JwtService;
import com.haui.demo.services.JwtUser;
import com.haui.demo.services.mappers.UserMapper;
import com.haui.demo.services.validators.UserValidator;
import com.haui.demo.utils.Global;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
public class UserService implements IUserService {

    private static final Logger logger = LogManager.getLogger(UserService.class);
    private final UserValidator validator;
    private final UserMapper mapper;
    private final UserRepository userRepository;
    private final JwtDistribute jwtDistribute;
    private final JwtService jwtService;
    private final JwtUser jwtUser;

    public UserService(UserValidator validator, UserMapper mapper, UserRepository userRepository, JwtDistribute jwtDistribute, JwtService jwtService, JwtUser jwtUser) {
        this.validator = validator;
        this.mapper = mapper;
        this.userRepository = userRepository;
        this.jwtDistribute = jwtDistribute;
        this.jwtService = jwtService;
        this.jwtUser = jwtUser;
    }

    @Override
    public ResponseEntity<SystemResponse<Object>> login(HttpServletRequest request, Login login) {

        User user = userRepository.findByUserNameAndPasswordAndStatus(login.getUserName(),login.getPassword(),Global.ACTIVE);
        if (user == null) return Response.unauthorized();
        String token = jwtUser.findNGenerateToken(user);
        UserLoginResponse response = new UserLoginResponse();
        response.setUser(user);
        response.setToken(token);
        return Response.ok(response);
    }

    @Override
    public boolean verifyRole(String roleId, String api) {

        return false;
    }
}
