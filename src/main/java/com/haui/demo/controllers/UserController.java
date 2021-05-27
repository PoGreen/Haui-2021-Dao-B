package com.haui.demo.controllers;

import com.haui.demo.models.bos.Panigation;
import com.haui.demo.models.bos.SystemResponse;
import com.haui.demo.models.requests.*;
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
        return service.login(request, login);
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
        return service.update(request, accountUpdateRq);
    }

    @GetMapping("/users")
    public ResponseEntity<SystemResponse<Object>> getOne(HttpServletRequest request) {
        return service.getOne(request);
    }

    @GetMapping("/users/filter")
    public ResponseEntity<SystemResponse<Object>> getAll(@RequestParam(value = "status") Integer status,
                                                         @RequestParam(value = "page", defaultValue = "1") Integer page,
                                                         @RequestParam(value = "limit", defaultValue = "20") Integer limit) {
        Panigation panigation = new Panigation();
        panigation.setPage(page);
        panigation.setLimit(limit);
        return service.getAll(status, panigation);
    }

    @PutMapping("/users/status")
    public ResponseEntity<SystemResponse<Object>> changeStatus(HttpServletRequest request,
                                                               @RequestBody StatusRq statusRq) {
        return service.changeStatus(request, statusRq);
    }

    @PutMapping("/forgot/password")
    public ResponseEntity<SystemResponse<Object>> forgotPassword(@RequestBody EmailForgot emailForgot) {
        return service.forgotPassword(emailForgot);
    }

    @DeleteMapping("/users/logout")
    public ResponseEntity<SystemResponse<Object>> logout(HttpServletRequest request) {
        return service.logout(request);
    }
}
