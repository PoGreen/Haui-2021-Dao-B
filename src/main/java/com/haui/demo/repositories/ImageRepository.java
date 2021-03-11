package com.haui.demo.repositories;

import com.haui.demo.models.entities.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import javax.transaction.Transactional;

public interface ImageRepository extends JpaRepository<Image, String> {

    @Transactional
    @Modifying
    void deleteByBuilding(String id);

    @Transactional
    @Modifying
    void deleteByNews(String id);
}
