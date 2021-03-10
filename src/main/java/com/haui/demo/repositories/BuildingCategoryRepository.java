package com.haui.demo.repositories;

import com.haui.demo.models.entities.BuildingCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BuildingCategoryRepository extends JpaRepository<BuildingCategory, String> {
    boolean existsByName(String name);
    boolean existsById(String id);
    boolean existsByIdAndStatus(String id, Integer status);
}
