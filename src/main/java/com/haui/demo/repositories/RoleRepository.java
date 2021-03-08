package com.haui.demo.repositories;

import com.haui.demo.models.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role,String> {
    Role findByRoleName(String roleName);
    Role findByRoleCode(String roleCode);
}
