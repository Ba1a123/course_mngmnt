package com.example.demo.repository;

// package com.example.course_mangmnt.repo;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import com.example.demo.models.Course;
import com.example.demo.models.User;

@Repository
public interface Courserepo extends JpaRepository<Course, Long> {
    List<Course> findByTeacherId(Long teacherId);
    List<Course> findByTeacher(User teacher);
}


