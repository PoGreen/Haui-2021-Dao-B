package com.haui.demo.repositories;

import com.haui.demo.models.entities.RoleApi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RoleApiRepository extends JpaRepository<RoleApi, String> {

    boolean existsByApiAndRole(String role, String api);

    @Query("SELECT r FROM RoleApi r JOIN apis a ON r.api = a.id WHERE a.url = ?1 AND r.role = ?2 AND a.method = ?3")
    RoleApi existsByRoleAndApiUrl(String url, String role, String method);


    List<RoleApi> findByRole(String role);
}
