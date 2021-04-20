package com.haui.demo.controllers;

import com.haui.demo.models.bos.SystemResponse;
import com.haui.demo.services.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller(value = "role-controller")
public class RoleController {

    @Autowired
    private IRoleService  service;

    @GetMapping("/roles")
    public ResponseEntity<SystemResponse<Object>> getALl() {
        return service.findAll();
    }
}
