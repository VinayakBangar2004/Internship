package com.intern.internship.controller;

import com.intern.internship.entity.*;
import com.intern.internship.repo.StudentFeeRepository;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/fees")
public class StudentFeeController {

    @Autowired
    private StudentFeeRepository feeRepository;

    @PostMapping
    public StudentFee addStudent(@RequestBody StudentFee studentFee) {
        return feeRepository.save(studentFee);
    }


    @GetMapping
    public List<StudentFee> getAllStudents() {
        return feeRepository.findAll();
    }


    @GetMapping("/{id}")
    public StudentFee getStudent(@PathVariable Long id) {
        return feeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found with id " + id));
    }

    @PutMapping("/{id}/pay")
    public ResponseEntity<StudentFee> payFee(@PathVariable Long id, @RequestParam Double amount) {
        return (ResponseEntity<StudentFee>) feeRepository.findById(id).map(studentFee -> {
            double newPaid = studentFee.getPaidFees() + amount;
            studentFee.setPaidFees(newPaid);
            return ResponseEntity.ok(feeRepository.save(studentFee));
        }).orElse(ResponseEntity.notFound().build());
    }


    @GetMapping("/{id}/remaining")
    public ResponseEntity<Double> getRemainingFees(@PathVariable Long id) {
        return feeRepository.findById(id)
                .map(studentFee -> ResponseEntity.ok(studentFee.getRemainingFees()))
                .orElse(ResponseEntity.notFound().build());
    }


}