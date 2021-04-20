package com.haui.demo.services.impls;

import com.haui.demo.models.bos.Response;
import com.haui.demo.models.bos.SystemResponse;
import com.haui.demo.models.entities.Role;
import com.haui.demo.models.responses.RoleRp;
import com.haui.demo.repositories.RoleRepository;
import com.haui.demo.services.IRoleService;
import com.haui.demo.services.mappers.RoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService implements IRoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private RoleMapper mapper;

    @Override
    public ResponseEntity<SystemResponse<Object>> findAll() {
        List<Role> roles = roleRepository.findAll();
        List<RoleRp> roleRps = mapper.map(roles);
        return Response.ok(roleRps);
    }
}
