package com.haui.demo.services.impls;

import com.haui.demo.models.bos.Response;
import com.haui.demo.models.bos.SystemResponse;
import com.haui.demo.models.entities.Role;
import com.haui.demo.models.entities.User;
import com.haui.demo.models.entities.Ward;
import com.haui.demo.models.requests.AdminRq;
import com.haui.demo.models.requests.Login;
import com.haui.demo.models.requests.SignupRq;
import com.haui.demo.models.responses.SignupRp;
import com.haui.demo.models.responses.UserLoginResponse;
import com.haui.demo.repositories.RoleRepository;
import com.haui.demo.repositories.UserRepository;
import com.haui.demo.repositories.WardRepository;
import com.haui.demo.services.IUserService;
import com.haui.demo.services.JwtDistribute;
import com.haui.demo.services.JwtService;
import com.haui.demo.services.JwtUser;
import com.haui.demo.services.mappers.UserMapper;
import com.haui.demo.services.validators.UserValidator;
import com.haui.demo.utils.Global;
import com.haui.demo.utils.StringResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

@Service
public class UserService implements IUserService {

    private static final Logger logger = LogManager.getLogger(UserService.class);

    @Autowired
    private UserValidator validator;
    @Autowired
    private UserMapper mapper;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JwtDistribute jwtDistribute;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private JwtUser jwtUser;
    @Autowired
    private WardRepository wardRepository;
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public ResponseEntity<SystemResponse<Object>> login(HttpServletRequest request, Login login) {

        User user = userRepository.findByUserNameAndPasswordAndStatus(login.getUserName(), login.getPassword(), Global.ACTIVE);
        if (user == null) return Response.unauthorized();
        String token = jwtUser.findNGenerateToken(user);
        UserLoginResponse response = new UserLoginResponse();
        response.setUser(user);
        response.setToken(token);
        return Response.ok(response);
    }

    @Override
    public ResponseEntity<SystemResponse<Object>> existByUserName(String userName) {
        boolean exist = userRepository.existsByUserName(userName);
        if (exist) {
            return Response.badRequest(StringResponse.USER_NAME_IS_EXIST);
        }
        return Response.ok(StringResponse.SUCCESS);
    }

    @Override
    public ResponseEntity<SystemResponse<Object>> signup(SignupRq signupRq) {
        ResponseEntity<SystemResponse<Object>> validateUserName = existByUserName(signupRq.getUserName());
        if (!validateUserName.getStatusCode().is2xxSuccessful()) {
            return validateUserName;
        }

        Ward ward = wardRepository.findById(signupRq.getWard()).orElse(null);
        if (Objects.isNull(ward)) {
            return Response.badRequest(StringResponse.WARD_IS_FAKE);
        }


        User user = mapper.map(signupRq, signupRq.getWard());

        Role role = roleRepository.findByRoleName(Global.ROLE_USER);
        user.setRole(role.getId());
        user.setStatus(Global.ACTIVE);
        userRepository.save(user);

        SignupRp signupRp = mapper.map(user);

        return Response.ok(signupRp);
    }

    @Override
    public ResponseEntity<SystemResponse<Object>> create(HttpServletRequest request, AdminRq adminRq) {

        ResponseEntity<SystemResponse<Object>> validateUserName = existByUserName(adminRq.getUserName());
        if (!validateUserName.getStatusCode().is2xxSuccessful()) {
            return validateUserName;
        }

        Ward ward = wardRepository.findById(adminRq.getWard()).orElse(null);
        if (Objects.isNull(ward)) {
            return Response.badRequest(StringResponse.WARD_IS_FAKE);
        }


        User user = mapper.map(adminRq, adminRq.getWard());
        Role role = roleRepository.findById(adminRq.getRole()).orElse(null);
        if (Objects.isNull(role)) {
            return Response.badRequest(StringResponse.ROLE_IS_FAKE);
        }
        user.setRole(role.getId());
        user.setStatus(Global.ACTIVE);
        userRepository.save(user);

        SignupRp signupRp = mapper.map(user);

        return Response.ok(signupRp);
    }
}
