package com.haui.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller(value = "login")
public class LoginController {
    @GetMapping(value = "/login")
    public String getLoginPage() {
        return "login.html";
    }
}
