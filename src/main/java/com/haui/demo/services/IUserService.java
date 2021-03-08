package com.haui.demo.services;



import com.haui.demo.models.bos.SystemResponse;
import com.haui.demo.models.requests.Login;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;

public interface IUserService {


    ResponseEntity<SystemResponse<Object>> login(HttpServletRequest request, Login login);
    boolean verifyRole(String roleId, String api);
}
