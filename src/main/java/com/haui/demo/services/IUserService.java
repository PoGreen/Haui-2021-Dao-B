package com.haui.demo.services;


import com.haui.demo.models.bos.Panigation;
import com.haui.demo.models.bos.SystemResponse;
import com.haui.demo.models.requests.*;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;

public interface IUserService {


    ResponseEntity<SystemResponse<Object>> login(HttpServletRequest request, Login login);

    ResponseEntity<SystemResponse<Object>> existByUserName(String userName);

    ResponseEntity<SystemResponse<Object>> signup(SignupRq signupRq);

    ResponseEntity<SystemResponse<Object>> create(HttpServletRequest request, AdminRq adminRq);

    ResponseEntity<SystemResponse<Object>> update(HttpServletRequest request, AccountUpdateRq accountUpdateRq);

    ResponseEntity<SystemResponse<Object>> getOne(HttpServletRequest request);

    ResponseEntity<SystemResponse<Object>> getAll(Integer status, Panigation panigation);

    ResponseEntity<SystemResponse<Object>> changeStatus(HttpServletRequest request, StatusRq statusRq);
}
