package com.haui.demo.services;

import com.haui.demo.models.bos.SystemResponse;
import com.haui.demo.models.requests.NewsCategoryRq;
import com.haui.demo.models.requests.StatusRq;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;

public interface INewsCategoryService {

    ResponseEntity<SystemResponse<Object>> getAllShow();

    ResponseEntity<SystemResponse<Object>> getAll();

    ResponseEntity<SystemResponse<Object>> addOne(HttpServletRequest request, NewsCategoryRq newsCategoryRq);

    ResponseEntity<SystemResponse<Object>> changeStatus(HttpServletRequest request, StatusRq statusRq);
}
