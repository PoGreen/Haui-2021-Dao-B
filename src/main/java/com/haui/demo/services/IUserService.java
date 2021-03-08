package com.haui.demo.services;


import com.haui.demo.models.bos.SystemResponse;
import com.haui.demo.models.requests.AccountUpdateRq;
import com.haui.demo.models.requests.AdminRq;
import com.haui.demo.models.requests.Login;
import com.haui.demo.models.requests.SignupRq;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;

public interface IUserService {


    ResponseEntity<SystemResponse<Object>> login(HttpServletRequest request, Login login);

    ResponseEntity<SystemResponse<Object>> existByUserName(String userName);

    ResponseEntity<SystemResponse<Object>> signup(SignupRq signupRq);
    ResponseEntity<SystemResponse<Object>> create(HttpServletRequest request, AdminRq adminRq);

    ResponseEntity<SystemResponse<Object>> update(HttpServletRequest request, AccountUpdateRq accountUpdateRq);
}
