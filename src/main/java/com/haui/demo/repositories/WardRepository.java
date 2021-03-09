package com.haui.demo.repositories;

import com.haui.demo.models.entities.Ward;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WardRepository extends JpaRepository<Ward,Integer> {
    List<Ward> findByDistrict(Integer districtId);
}
