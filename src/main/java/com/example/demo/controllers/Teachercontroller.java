package com.example.demo.controllers;
// package com.example.course_mangmnt.controllers;

import com.example.demo.models.Course;
import com.example.demo.models.User;
import com.example.demo.services.Courseservice;
import com.example.demo.services.userService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/teachers")
public class Teachercontroller {

    @Autowired
    private userService userService;

    @Autowired
    private Courseservice courseService;

    @GetMapping
    public ResponseEntity<List<User>> getAllTeachers() {
        List<User> teachers = userService.getUsersByRole("TEACHER");
        return ResponseEntity.ok(teachers);
    }

    @GetMapping("/{id}/courses")
    public ResponseEntity<List<Course>> getCoursesByTeacher(@PathVariable Long id) {
        List<Course> courses = courseService.getCoursesByTeacherId(id);
        return ResponseEntity.ok(courses);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateTeacher(@PathVariable Long id, @RequestBody User teacherDetails) {
        User updatedTeacher = userService.updateUser(id, teacherDetails, "TEACHER");
        return ResponseEntity.ok(updatedTeacher);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<User> deleteTeacher(@PathVariable Long id) {
        User deletedTeacher = userService.deleteUser(id, "TEACHER");
        return ResponseEntity.ok(deletedTeacher);
    }
}

