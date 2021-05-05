package com.haui.demo.controllers.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller(value = "web-buildings")
public class BuildingsController {

    @GetMapping(value = "/buildings-buy-page")
    public String getBuildingsRentPages() {
        return "/buildings-rent";

    }
    @GetMapping(value = "/buildings-rent-page")
    public String getBuildingsBuyPages() {
        return "/buildings-buy";
    }

    @GetMapping(value = "/buildings-list-page")
    public String getBuildingsTable() {
        return "/user-buildings";
    }
}
