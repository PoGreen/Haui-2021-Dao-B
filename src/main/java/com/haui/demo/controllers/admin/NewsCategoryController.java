package com.haui.demo.controllers.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller(value = "admin-news-category")
public class NewsCategoryController {

    @GetMapping(value = "/admin/new-news-categories")
    public String getNewsCategoryPage() {
        return "admin/news-category.html";
    }

    @GetMapping(value = "/admin/news-categories")
    public String getNewsCategoryTable() {
        return "admin/list-news-category.html";
    }


}
