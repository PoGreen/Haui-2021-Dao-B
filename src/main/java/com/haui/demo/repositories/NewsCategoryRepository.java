package com.haui.demo.repositories;

import com.haui.demo.models.entities.NewsCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NewsCategoryRepository extends JpaRepository<NewsCategory, String> {

    List<NewsCategory> findByStatus(Integer status);

    boolean existsByName(String name);

    boolean existsById(String id);
}
