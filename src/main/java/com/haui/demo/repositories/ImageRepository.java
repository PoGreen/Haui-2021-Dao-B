package com.haui.demo.repositories;

import com.haui.demo.models.entities.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import javax.transaction.Transactional;
import java.util.List;

public interface ImageRepository extends JpaRepository<Image, String> {

    @Transactional
    @Modifying
    void deleteByBuilding(String id);

    @Transactional
    @Modifying
    void deleteByNews(String id);

    List<Image> findByNews(String id);

    Image findFirstByNews(String id);

    List<Image> findByBuilding(String id);

    Image findFirstByBuilding(String id);
}
