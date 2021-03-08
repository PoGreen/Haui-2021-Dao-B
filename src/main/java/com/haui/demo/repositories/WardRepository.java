package com.haui.demo.repositories;

import com.haui.demo.models.entities.Ward;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WardRepository extends JpaRepository<Ward,Integer> {
}
