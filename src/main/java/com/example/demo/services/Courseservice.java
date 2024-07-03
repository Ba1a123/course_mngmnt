package com.example.demo.services;

import com.example.demo.models.Course;
import com.example.demo.models.User;
import com.example.demo.repository.Courserepo;
import com.example.demo.repository.userrepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Courseservice {

    @Autowired
    private Courserepo courseRepository;

    @Autowired
    private userrepo userRepository;

    public Course addCourse(Course course, String role) {
        // Example: Check if the role is ADMIN before allowing course creation
        if (!"ADMIN".equalsIgnoreCase(role)) {
            throw new SecurityException("Only admins can add courses");
        }
        return courseRepository.save(course);
    }

    public Course deleteCourse(Long id, String role) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Course not found"));

        // Check if the role is authorized to delete the course
        if (!role.equalsIgnoreCase("ADMIN")) {
            throw new SecurityException("Only admins can delete courses");
        }

        // Delete the course
        courseRepository.delete(course);

        // Return the deleted course
        return course;
    }

    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    public Course updateCourse(Long id, Course courseDetails, String role) {
        // Example: Check if the role is ADMIN before allowing course update
        if (!"ADMIN".equalsIgnoreCase(role)) {
            throw new SecurityException("Only admins can update courses");
        }

        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Course not found"));

        course.setTitle(courseDetails.getTitle());
        course.setDescription(courseDetails.getDescription());

        // Update teacher if provided in the courseDetails
        if (courseDetails.getTeacher() != null && courseDetails.getTeacher().getId() != null) {
            User teacher = userRepository.findById(courseDetails.getTeacher().getId())
                    .orElseThrow(() -> new RuntimeException("Teacher not found"));
            course.setTeacher(teacher);
        }

        return courseRepository.save(course);
    }
    public List<Course> getCoursesByTeacherId(Long teacherId) {
        User teacher = userRepository.findById(teacherId).orElseThrow(() -> new RuntimeException("Teacher not found"));
        return courseRepository.findByTeacher(teacher);
    }
    public Course getCourseById(Long id) {
        return courseRepository.findById(id).orElseThrow(() -> new RuntimeException("Course not found"));
    }
}
