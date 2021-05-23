package com.haui.demo.repositories;

import com.haui.demo.models.entities.News;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface NewsRepository extends JpaRepository<News, String> {

    Page<News> findByStatus(Integer status, Pageable pageable);

    Page<News> findByNewsCategoryAndStatus(String id, int status, Pageable pageable);

    @Transactional
    @Modifying
    @Query("UPDATE news n SET n.status = ?2 WHERE n.id = ?1")
    void deleteOne(String id, int status);
}
