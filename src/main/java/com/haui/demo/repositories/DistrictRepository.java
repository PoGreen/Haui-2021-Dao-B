package com.haui.demo.repositories;


import com.haui.demo.models.entities.District;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DistrictRepository extends JpaRepository<District,Integer> {
    List<District> findByProvince(Integer provinceId);
}
