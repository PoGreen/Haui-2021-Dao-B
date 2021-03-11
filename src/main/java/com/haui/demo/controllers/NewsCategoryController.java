package com.haui.demo.controllers;

import com.haui.demo.models.bos.SystemResponse;
import com.haui.demo.models.requests.NewsCategoryRq;
import com.haui.demo.models.requests.StatusRq;
import com.haui.demo.services.INewsCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
public class NewsCategoryController {

    @Autowired
    private INewsCategoryService newsCategorService;

    @GetMapping("/web/news-categories")
    public ResponseEntity<SystemResponse<Object>> getALlShow() {
        return newsCategorService.getAllShow();
    }

    @GetMapping("/news-categories")
    public ResponseEntity<SystemResponse<Object>> getALl() {
        return newsCategorService.getAll();
    }

    @PostMapping("/news-categories")
    public ResponseEntity<SystemResponse<Object>> addOne(HttpServletRequest request, @RequestBody NewsCategoryRq newsCategoryRq) {
        return newsCategorService.addOne(request, newsCategoryRq);
    }

    @PutMapping("/news-categories")
    public ResponseEntity<SystemResponse<Object>> changeStatus(HttpServletRequest request,
                                                               @RequestBody StatusRq statusRq) {
        return newsCategorService.changeStatus(request,statusRq);
    }
}
