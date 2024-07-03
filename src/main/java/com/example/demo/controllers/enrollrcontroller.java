package com.example.demo.controllers;
// package com.example.course_mangmnt.controllers;

import com.example.demo.models.User;
import com.example.demo.models.Enrollment;
import com.example.demo.services.enrollservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/enrollments")
public class enrollrcontroller {

    @Autowired
    private enrollservice enrollmentService;

    @PostMapping("/{courseId}")
    public Enrollment enrollStudentInCourse(@RequestHeader("role") String role, @PathVariable Long courseId, @RequestParam Long studentId) {
        return enrollmentService.enrollStudentInCourse(courseId, studentId, role);
    }

    @GetMapping("/{courseId}")
    public List<User> getStudentsByCourse(@RequestHeader("role") String role, @PathVariable Long courseId) {
        return enrollmentService.getStudentsByCourse(courseId, role);
    }

    @DeleteMapping("/{courseId}/{studentId}")
    public User removeStudentFromCourse(@RequestHeader("role") String role, @PathVariable Long courseId, @PathVariable Long studentId) {
        return enrollmentService.removeStudentFromCourse(courseId, studentId, role);
    }
     @GetMapping("")
    public ResponseEntity<String> testEndpoint() {
        return ResponseEntity.ok("Test endpoint is working");
    }
}
