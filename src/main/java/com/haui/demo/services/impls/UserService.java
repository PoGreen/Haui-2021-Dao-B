package com.haui.demo.services.impls;

import com.haui.demo.models.bos.Panigation;
import com.haui.demo.models.bos.Response;
import com.haui.demo.models.bos.SystemResponse;
import com.haui.demo.models.entities.NewsCategory;
import com.haui.demo.models.entities.Role;
import com.haui.demo.models.entities.User;
import com.haui.demo.models.entities.Ward;
import com.haui.demo.models.requests.*;
import com.haui.demo.models.responses.NewsCategoryRp;
import com.haui.demo.models.responses.SignupRp;
import com.haui.demo.models.responses.UserLoginResponse;
import com.haui.demo.models.responses.UserRp;
import com.haui.demo.repositories.RoleRepository;
import com.haui.demo.repositories.UserRepository;
import com.haui.demo.repositories.WardRepository;
import com.haui.demo.services.*;
import com.haui.demo.services.mappers.UserMapper;
import com.haui.demo.services.validators.UserValidator;
import com.haui.demo.utils.Global;
import com.haui.demo.utils.StringResponse;
import com.haui.demo.utils.Utils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
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

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private EmailService emailService;

    @Override
    public ResponseEntity<SystemResponse<Object>> login(HttpServletRequest request, Login login) {


        User user = userRepository.findByUserNameAndStatus(login.getUserName(), Global.ACTIVE);

        if (user == null) return Response.unauthorized();
        if (!user.comparePassword(login.getPassword(), passwordEncoder)) return Response.unauthorized();

        String token = jwtUser.findNGenerateToken(user);
        UserLoginResponse response = new UserLoginResponse();
        response.setUser(user);
        response.setToken(token);

        Role role = roleRepository.findById(user.getRole()).orElse(null);

        response.setRole(role.getRoleName());
        return Response.ok(response);
    }

    @Override
    public ResponseEntity<SystemResponse<Object>> logout(HttpServletRequest request) {
        User user = jwtUser.getUser(request);
        if (user != null) jwtUser.revokeToken(user.getId());
        return Response.ok();
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
        user.setPassword(signupRq.getPassword(), passwordEncoder);
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
        user.setPassword(adminRq.getPassword(), passwordEncoder);
        userRepository.save(user);

        SignupRp signupRp = mapper.map(user);

        return Response.ok(signupRp);
    }

    @Override
    public ResponseEntity<SystemResponse<Object>> update(HttpServletRequest request, AccountUpdateRq accountUpdateRq) {

        User user = jwtUser.getUser(request);

        if (Objects.isNull(user)) {
            return Response.badRequest(StringResponse.USER_ID_FAKE);
        }

        Ward ward = wardRepository.findById(accountUpdateRq.getWard()).orElse(null);
        if (Objects.isNull(ward)) {
            return Response.badRequest(StringResponse.WARD_IS_FAKE);
        }

        mapper.map(user, accountUpdateRq);
        user.setPassword(user.getPassword(), passwordEncoder);
        userRepository.save(user);
        SignupRp signupRp = mapper.map(user);
        return Response.ok(signupRp);
    }

    @Override
    public ResponseEntity<SystemResponse<Object>> getOne(HttpServletRequest request) {
        User user = jwtUser.getUser(request);
        if (Objects.isNull(user)) {
            return Response.badRequest(StringResponse.USER_ID_FAKE);
        }
        UserRp userRp = mapper.mapRp(user);
        return Response.ok(userRp);
    }

    @Override
    public ResponseEntity<SystemResponse<Object>> getAll(Integer status, Panigation panigation) {

        Pageable pageable = PageRequest.of(panigation.getPage() - 1, panigation.getLimit());
        Page<User> users = Page.empty();
        switch (status) {
            case 1:
                users = userRepository.findByStatus(Global.ACTIVE, pageable);
                break;
            case 3:
                users = userRepository.findByStatus(Global.NOACTIVE, pageable);
                break;
            default:
                users = userRepository.findAll(pageable);
                break;
        }
        Page<UserRp> userRps = mapper.map(users);
        return Response.ok(userRps);
    }

    @Override
    public ResponseEntity<SystemResponse<Object>> changeStatus(HttpServletRequest request, StatusRq statusRq) {
        User user = userRepository.findById(statusRq.getId()).orElse(null);
        if (Objects.isNull(user)) {
            return Response.badRequest(StringResponse.USER_ID_FAKE);
        }
        User userAdmin = jwtUser.getUser(request);
        if (!Objects.isNull(userAdmin)) {
            user.setUpdatedBy(userAdmin.getId());
        }
        if (statusRq.getStatus() != Global.NOACTIVE && statusRq.getStatus() != Global.ACTIVE) {
            return Response.badRequest(StringResponse.STATUS_IS_FAKE);
        }
        if (statusRq.getStatus() == Global.ACTIVE) {
            user.setStatus(Global.ACTIVE);
        }
        if (statusRq.getStatus() == Global.NOACTIVE) {
            user.setStatus(Global.NOACTIVE);
        }
        userRepository.save(user);
        return Response.ok();

    }

    @Override
    public ResponseEntity<SystemResponse<Object>> forgotPassword(EmailForgot emailForgot) {

        User user = userRepository.findByUserNameAndEmail(emailForgot.getUserName(), emailForgot.getEmail());

        if (user == null) return Response.badRequest(StringResponse.USER_ID_FAKE);
        String newPassword = generateOTP(6);
        emailService.sendEmailPassword(user.getEmail(), newPassword);
        user.setPassword(newPassword, passwordEncoder);
        userRepository.save(user);
        return Response.ok();

    }

    public static String generateOTP(int len) {
        if (len <= 0) len = 6;
        StringBuilder otp = new StringBuilder();
        SecureRandom random = new SecureRandom();
        for (int i = 0; i < len; i++) {
            otp.append(random.nextInt(9));
        }
        return otp.toString();
    }
}
