package com.example.fitmeal.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "basal_metabolic_rates")
public class BasalMetabolicRate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String gender;

    @Column(nullable = false)
    private Integer ageGroup;
    
    @Column(nullable = false)
    private Integer bmrCalories;
    
}
