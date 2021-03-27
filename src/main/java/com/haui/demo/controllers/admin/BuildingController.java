package com.haui.demo.controllers.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller(value = "admin-buildings-controller")
public class BuildingController {
    @GetMapping(value = "/admin/new-buildings")
    public String getNewBuildingPage() {
        return "admin/buildings.html";
    }

    @GetMapping(value = "/admin/buildings")
    public String getBuildingPage() {
        return "admin/list-buildings.html";
    }
}
