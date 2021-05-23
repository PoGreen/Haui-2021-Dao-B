package com.haui.demo.controllers.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller(value = "news-controller")
public class NewsController {

    @GetMapping(value = "/news-page")
    public String getNewsPage() {
        return "/news-page";
    }

    @GetMapping(value = "/news-page-detail")
    public String getNewsDetailPage() {
        return "/news-details-page.html";
    }

}
