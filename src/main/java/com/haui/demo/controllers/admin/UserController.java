package com.haui.demo.controllers.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller(value = "admin-controller")
public class UserController {

    @GetMapping(value = "/admin/list-users-page")
    public String getAdminsPage() {
        return "admin/list-users.html";
    }

    @GetMapping(value = "/admin/users-page")
    public String getCreateAdminsPage() {
        return "admin/create-admin.html";
    }
}
