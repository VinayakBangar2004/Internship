package com.intern.internship.service;


import com.intern.internship.entity.*;
import com.intern.internship.repo.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class StudentTeacherService {

    @Autowired
    private StudentRepository studentRepo;

    @Autowired
    private TeacherRepository teacherRepo;

    // Assign teachers to student
    public String assignTeachers(Long studentId, List<Long> teacherIds) {
        Student student = studentRepo.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        List<Teacher> teachersToAssign = new ArrayList<>(teacherRepo.findAllById(teacherIds));

        student.getTeachers().addAll(teachersToAssign);

        if(studentRepo.save(student)!=null){
            return "teacher assign sucessfully";
        }
        return "teacher is not assign";
    }

    public Student getStudent(Long id) {
        return studentRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found"));
    }

}
