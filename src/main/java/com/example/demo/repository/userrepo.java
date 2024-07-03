package com.example.demo.repository;
// package com.example.course_mangmnt.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.models.User;

import java.util.*;

@Repository
public interface userrepo extends JpaRepository<User, Long> {
    User findByUsername(String username);
    // User findByUsername(String username);
    List<User> findByRole(String role);
    User findByName(String name);

}

