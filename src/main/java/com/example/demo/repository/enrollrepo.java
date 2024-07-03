package com.example.demo.repository;
// package com.example.course_mangmnt.repo;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.models.Course;
import com.example.demo.models.Enrollment;
import com.example.demo.models.User;

import java.util.*;

@Repository
public interface enrollrepo extends JpaRepository<Enrollment, Long> {
    List<Enrollment> findByStudentId(Long studentId);
    List<Enrollment> findByCourse(Course course);
    Enrollment findByCourseAndStudent(Course course, User student);
}
