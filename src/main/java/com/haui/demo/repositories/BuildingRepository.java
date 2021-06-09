package com.haui.demo.repositories;

import com.haui.demo.models.entities.Building;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;


public interface BuildingRepository extends JpaRepository<Building, String> {

    @Query("SELECT b FROM buildings b JOIN buildingCategories bc oN b.buildingCategory = bc.id WHERE b.status = ?1 AND bc.status = ?2")
    Page<Building> findByStatusAndBuildingCategoryStatus(Integer status, Integer statusBc, Pageable pageable);

    @Query("SELECT b FROM buildings b WHERE b.createdBy = ?1")
    Page<Building> findByCreated_by(String id, Pageable pageable);

    Page<Building> findByCreatedByAndUserAndStatusAndSaleRent(String created_by, String user, int status, int saleREnt, Pageable pageable);

}
