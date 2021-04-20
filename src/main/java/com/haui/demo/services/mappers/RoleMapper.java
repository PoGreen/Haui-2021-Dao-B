package com.haui.demo.services.mappers;

import com.haui.demo.models.entities.Role;
import com.haui.demo.models.responses.RoleRp;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class RoleMapper {

    public List<RoleRp> map(List<Role> roles) {
        return roles.stream().map(it -> {
            RoleRp roleRp = new RoleRp();
            roleRp.setId(it.getId());
            roleRp.setName(it.getRoleName());
            return roleRp;
        }).collect(Collectors.toList());
    }
}
