package com.haui.demo.repositories;

import com.haui.demo.models.entities.News;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NewsRepository extends JpaRepository<News, String> {

    Page<News> findByStatus(Integer status, Pageable pageable);
}
