package com.haui.demo.controllers.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller(value = "admin-home")
public class HomeController {
    @GetMapping(value = "/admin/home")
    public String getHomePage(){
        return "admin/home.html";
    }
}
