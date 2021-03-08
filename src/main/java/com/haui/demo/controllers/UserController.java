package com.haui.demo.controllers;

import com.haui.demo.models.bos.SystemResponse;
import com.haui.demo.models.requests.Login;
import com.haui.demo.services.IUserService;
import io.swagger.annotations.Api;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@Api(tags = "USER")
@RestController
@RequestMapping("/users")
public class UserController {

    private final IUserService service;

    public UserController(IUserService service) {
        this.service = service;
    }

    @PostMapping("/login")
    public ResponseEntity<SystemResponse<Object>> login(HttpServletRequest request, @RequestBody Login login) {
        return service.login(request,login);
    }

}
