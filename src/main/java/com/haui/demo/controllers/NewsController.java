package com.haui.demo.controllers;

import com.haui.demo.models.bos.Panigation;
import com.haui.demo.models.bos.SystemResponse;
import com.haui.demo.models.requests.NewsCategoryRq;
import com.haui.demo.models.requests.NewsRq;
import com.haui.demo.services.INewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller(value = "news")
public class NewsController {

    @Autowired
    private INewsService newsService;

    @PostMapping("/news")
    public ResponseEntity<SystemResponse<Object>> addOne(HttpServletRequest request, @RequestBody NewsRq newsRq) {
        return newsService.addOne(request, newsRq);
    }

    @PutMapping("/news")
    public ResponseEntity<SystemResponse<Object>> updateOne(HttpServletRequest request, @RequestBody NewsRq newsRq) {
        return newsService.update(request, newsRq);
    }

    @GetMapping("/news/{id}")
    public ResponseEntity<SystemResponse<Object>> getOne(HttpServletRequest request, @PathVariable(name = "id") String id) {
        return newsService.getOne(request, id);
    }

    @GetMapping("/news")
    public ResponseEntity<SystemResponse<Object>> getAll(@RequestParam(value = "page") int page,
                                                         @RequestParam(value = "limit") int limit) {
        Panigation panigation = new Panigation();
        panigation.setPage(page);
        panigation.setLimit(limit);
        return newsService.getAll(panigation);
    }

    @GetMapping("/news/status")
    public ResponseEntity<SystemResponse<Object>> getAllByStaus(@RequestParam(name = "status") int status,
                                                                @RequestParam(value = "page") int page,
                                                                @RequestParam(value = "limit") int limit) {
        Panigation panigation = new Panigation();
        panigation.setPage(page);
        panigation.setLimit(limit);
        return newsService.getAllByStatus(status, panigation);
    }
}
