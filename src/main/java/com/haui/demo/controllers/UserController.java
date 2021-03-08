package com.haui.demo.controllers;

import com.haui.demo.models.bos.SystemResponse;
import com.haui.demo.models.requests.AccountUpdateRq;
import com.haui.demo.models.requests.AdminRq;
import com.haui.demo.models.requests.Login;
import com.haui.demo.models.requests.SignupRq;
import com.haui.demo.services.IUserService;
import io.swagger.annotations.Api;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Api(tags = "USER")
@RestController
public class UserController {

    private final IUserService service;

    public UserController(IUserService service) {
        this.service = service;
    }

    @PostMapping("/users/login")
    public ResponseEntity<SystemResponse<Object>> login(HttpServletRequest request, @RequestBody Login login) {
        return service.login(request,login);
    }
    @PostMapping("/users/signup")
    public ResponseEntity<SystemResponse<Object>> signup(@RequestBody SignupRq signupRq) {
        return service.signup(signupRq);
    }
    @PostMapping("/users")
    public ResponseEntity<SystemResponse<Object>> create(HttpServletRequest request, @RequestBody AdminRq adminRq) {
        return service.create(request, adminRq);
    }

    @PutMapping("/users")
    public ResponseEntity<SystemResponse<Object>> update(HttpServletRequest request, @RequestBody AccountUpdateRq accountUpdateRq) {
        return service.update(request,accountUpdateRq);
    }

    @GetMapping("/users")
    public ResponseEntity<SystemResponse<Object>> getOne(HttpServletRequest request) {
        return service.getOne(request);
    }
}
