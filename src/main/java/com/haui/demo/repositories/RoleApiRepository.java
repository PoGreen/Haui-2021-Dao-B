package com.haui.demo.repositories;

import com.haui.demo.models.entities.RoleApi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RoleApiRepository extends JpaRepository<RoleApi,String> {

    boolean existsByApiAndRole(String role, String api);

    @Query("SELECT r FROM RoleApi r JOIN apis a ON r.api = a.id WHERE a.url = ?1 AND r.role = ?2")
    RoleApi existsByRoleAndApiUrl(String url, String role);
}
