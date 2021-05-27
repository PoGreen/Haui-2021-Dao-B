package com.haui.demo.controllers;


import com.haui.demo.models.bos.Panigation;
import com.haui.demo.models.bos.Response;
import com.haui.demo.models.bos.SystemResponse;
import com.haui.demo.services.AuthorizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller(value = "authorization-controller")
public class AuthorizationController {

    @Autowired
    private AuthorizationService authorizationService;

    @GetMapping("/verify-role")
    public ResponseEntity<SystemResponse<Object>> getAll(HttpServletRequest request,
                                                         HttpServletResponse response,
                                                         @RequestParam(value = "url") String url,
                                                         @RequestParam(value = "method") String method) throws Exception {

        return Response.ok(authorizationService.verify(request, response, url, method));
    }
}
