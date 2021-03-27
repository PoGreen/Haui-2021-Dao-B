package com.haui.demo.controllers.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller(value = "home")
public class HomeController {

    @GetMapping(value = "/home")
    public String getHomePage(){
        return "/home";
    }
}
