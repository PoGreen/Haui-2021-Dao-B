package com.haui.demo.services;

import com.haui.demo.models.bos.Panigation;
import com.haui.demo.models.bos.SystemResponse;
import com.haui.demo.models.requests.NewsRq;
import com.haui.demo.models.requests.StatusRq;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;

public interface INewsService {

    ResponseEntity<SystemResponse<Object>> getAll(Panigation panigation);

    ResponseEntity<SystemResponse<Object>> getAllByStatus(Integer status,Panigation panigation);

    ResponseEntity<SystemResponse<Object>> addOne(HttpServletRequest request, NewsRq newsRq);

    ResponseEntity<SystemResponse<Object>> update(HttpServletRequest request, NewsRq newsRq);

    ResponseEntity<SystemResponse<Object>> changeStatus(HttpServletRequest request, StatusRq statusRq);

    ResponseEntity<SystemResponse<Object>> getOne(HttpServletRequest request, String id);
}
