package com.intern.internship.controller;


import com.intern.internship.entity.Student;
import com.intern.internship.entity.Teacher;
import com.intern.internship.repo.StudentRepository;
import com.intern.internship.repo.TeacherRepository;
import com.intern.internship.service.StudentTeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api")
public class StudentTeacherController {
    @Autowired
    StudentRepository studentRepository;

    @Autowired
    TeacherRepository teacherRepository;

    @Autowired
    StudentTeacherService service;


    @PostMapping("/students/{studentId}/teachers")
    public ResponseEntity<String> assignTeachers(@PathVariable Long studentId, @RequestBody List<Long> teacherIds) {
        String msg= service.assignTeachers(studentId, teacherIds);
        return new ResponseEntity<>(msg, HttpStatus.OK);
    }


    @GetMapping("/students/{id}")
    public Student getStudent(@PathVariable Long id) {
        return service.getStudent(id);
    }



    @PostMapping("/students")
    public Student createStudent(@RequestBody Student student) {
        return studentRepository.save(student);
    }


    @PostMapping("/teachers")
    public Teacher createTeacher(@RequestBody Teacher teacher) {
        return teacherRepository.save(teacher);
    }
}
