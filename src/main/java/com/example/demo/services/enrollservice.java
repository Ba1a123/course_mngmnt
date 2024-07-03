package com.example.demo.services;
// package com.example.course_mangmnt.services;

import com.example.demo.models.Course;
import com.example.demo.models.Enrollment;
import com.example.demo.models.User;
import com.example.demo.repository.Courserepo;
import com.example.demo.repository.enrollrepo;
import com.example.demo.repository.userrepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class enrollservice {

    @Autowired
    private enrollrepo enrollmentRepository;

    @Autowired
    private Courserepo courseRepository;

    @Autowired
    private userrepo userRepository;

    public Enrollment enrollStudentInCourse(Long courseId, Long studentId, String role) {
        if (!role.equalsIgnoreCase("ADMIN") && !role.equalsIgnoreCase("STUDENT")) {
            throw new SecurityException("Only admins and students can enroll in courses");
        }
        
        Course course = courseRepository.findById(courseId).orElseThrow(() -> new RuntimeException("Course not found"));
        User student = userRepository.findById(studentId).orElseThrow(() -> new RuntimeException("Student not found"));
        
        Enrollment enrollment = new Enrollment();
        enrollment.setCourse(course);
        enrollment.setStudent(student);
        
        return enrollmentRepository.save(enrollment);
        
    }

    public List<User> getStudentsByCourse(Long courseId, String role) {
        if (!role.equalsIgnoreCase("ADMIN") && !role.equalsIgnoreCase("TEACHER")) {
            throw new SecurityException("Only admins and teachers can view students by course");
        }

        Course course = courseRepository.findById(courseId).orElseThrow(() -> new RuntimeException("Course not found"));
        return enrollmentRepository.findByCourse(course).stream()
                .map(Enrollment::getStudent)
                .collect(Collectors.toList());
    }

    public User removeStudentFromCourse(Long courseId, Long studentId, String role) {
        if (!role.equalsIgnoreCase("ADMIN") && !role.equalsIgnoreCase("TEACHER")) {
            throw new SecurityException("Only admins and teachers can remove students from courses");
        }

        Course course = courseRepository.findById(courseId).orElseThrow(() -> new RuntimeException("Course not found"));
        User student = userRepository.findById(studentId).orElseThrow(() -> new RuntimeException("Student not found"));
        
        Enrollment enrollment = enrollmentRepository.findByCourseAndStudent(course, student);
        if (enrollment != null) {
            enrollmentRepository.delete(enrollment);
            return student;
        }
        throw new RuntimeException("Enrollment not found");
    }
}
