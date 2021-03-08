package com.haui.demo.repositories;

import com.haui.demo.models.entities.Api;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApiRepository extends JpaRepository<Api,String> {
    Api findByUrl(String url);
}
