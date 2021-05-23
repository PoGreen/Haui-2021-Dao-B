package com.haui.demo.controllers;

import com.haui.demo.models.bos.Response;
import com.haui.demo.models.bos.SystemResponse;
import com.haui.demo.services.RssService;
import com.rometools.rome.io.FeedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;

@Controller(value = "rss - controller")
public class RssController {

    @Autowired
    private RssService rssService;

    @GetMapping("/rss-news")
    public ResponseEntity<SystemResponse<Object>> getOne(@RequestParam(value = "url") String url) throws FeedException, IOException {
        return Response.ok(rssService.readRss(url));
    }

    @GetMapping("/rss-new-page")
    public String getPage() {
        return "/rss-news-page.html";
    }

}
