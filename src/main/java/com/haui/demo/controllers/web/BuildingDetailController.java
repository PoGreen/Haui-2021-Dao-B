package com.haui.demo.controllers.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller(value = "web-buildings-controller")
public class BuildingDetailController {

    @GetMapping(value = "/buildings-detail")
    public String getBuildingDetail(){
        return "/builingdetail";
    }

}
