package com.intern.internship.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class StudentFee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String studentName;

    private Double totalFees;

    private Double paidFees = 0.0;  // initially 0

    public Double getRemainingFees() {
        return totalFees - paidFees;
    }
}