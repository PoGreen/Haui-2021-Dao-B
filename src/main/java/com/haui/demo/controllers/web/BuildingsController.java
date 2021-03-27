package com.haui.demo.controllers.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller(value = "web-buildings")
public class BuildingsController {

    @GetMapping(value = "/buildings-page")
    public String getBuildingsPages() {
        return "/buildings";

    }
}
