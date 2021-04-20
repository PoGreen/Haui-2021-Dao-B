package com.haui.demo.repositories;


import com.haui.demo.models.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, String> {

    User findByUserNameAndPasswordAndStatus(String userName, String password, Integer status);

    boolean existsByUserName(String userName);

    Page<User> findByStatus(Integer status, Pageable pageable);
}
