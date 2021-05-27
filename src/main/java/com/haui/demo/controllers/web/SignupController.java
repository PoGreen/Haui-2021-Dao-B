package com.haui.demo.controllers.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller(value = "signup")
public class SignupController {
    @GetMapping(value = "/signup")
    public String getHomePage(){
        return "/register.html";
    }

    @GetMapping(value = "/edit-info")
    public String getEditPage(){
        return "/register-edit.html";
    }
}
