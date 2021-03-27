package com.haui.demo.controllers.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller(value = "admin-news-controller")
public class NewsController {
    @GetMapping(value = "/admin/news")
    public String getBuildingPage() {
        return "admin/list-news.html";
    }
}
