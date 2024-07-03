package com.example.demo.controllers;
// package com.example.course_mangmnt.controllers;
// package com.example.course_management.controllers;

import com.example.demo.models.Course;
import com.example.demo.models.User;
import com.example.demo.services.Courseservice;
import com.example.demo.services.userService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class studentController {

    @Autowired
    private userService userService;

    @Autowired
    private Courseservice courseService;

    // @GetMapping("/{studentId}/courses")
    // public ResponseEntity<List<Course>> getEnrolledCourses(@PathVariable Long studentId) {
    //     User student = userService.getUserById(studentId);
    //     if (student == null || !student.getRole().equals("STUDENT")) {
    //         return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    //     }
    //     return ResponseEntity.ok(student.getEnrollments().stream().map(enrollment -> enrollment.getCourse()).toList());
    // }

    @GetMapping("/{studentId}/faculty/{courseId}")
    public ResponseEntity<User> getConcernedFaculty(@PathVariable Long studentId, @PathVariable Long courseId) {
        Course course = courseService.getCourseById(courseId);
        if (course == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(course.getTeacher());
    }

    @PutMapping("/{studentId}")
    public ResponseEntity<String> editProfile(@PathVariable Long studentId, @RequestBody User updatedDetails) {
        try {
            User updatedStudent = userService.updateUser(studentId, updatedDetails, "STUDENT");
            return ResponseEntity.ok("Profile updated successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Profile update failed: " + e.getMessage());
        }
    }
}

