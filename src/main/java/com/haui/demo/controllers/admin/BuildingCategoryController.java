package com.haui.demo.controllers.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller(value = "admin-building-category")
public class BuildingCategoryController {

    @GetMapping(value = "/admin/new-buildings-categories")
    public String getBuildingCategory() {
        return "admin/buildings-category.html";
    }

    @GetMapping(value = "/admin/buildings-categories")
    public String getBuildingCategoryPage() {
        return "admin/list-buildings-category.html";
    }
}
