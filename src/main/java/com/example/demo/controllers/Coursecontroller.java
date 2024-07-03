package com.example.demo.controllers;

import com.example.demo.models.Course;
import com.example.demo.services.Courseservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/courses")
public class Coursecontroller {

    @Autowired
    private Courseservice courseService;

    // Endpoint to add a course (Admin)
    @PostMapping
    public ResponseEntity<Course> addCourse(@RequestHeader("role") String role, @RequestBody Course course) {
        Course newCourse = courseService.addCourse(course, role);
        return ResponseEntity.ok(newCourse);
    }

    // Endpoint to delete a course (Admin)
    @DeleteMapping("/{id}")
    public ResponseEntity<Course> deleteCourse(@RequestHeader("role") String role, @PathVariable Long id) {
        Course deletedCourse = courseService.deleteCourse(id, role);
        if (deletedCourse != null) {
            return ResponseEntity.ok(deletedCourse);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Endpoint to update a course (Admin)
    @PutMapping("/{id}")
    public ResponseEntity<Course> updateCourse(@RequestHeader("role") String role, @PathVariable Long id, @RequestBody Course courseDetails) {
        Course updatedCourse = courseService.updateCourse(id, courseDetails, role);
        return ResponseEntity.ok(updatedCourse);
    }

    // Endpoint to get all courses (Admin, Teacher, Student)
    @GetMapping
    public ResponseEntity<List<Course>> getAllCourses() {
        List<Course> courses = courseService.getAllCourses();
        return ResponseEntity.ok(courses);
    }
}
