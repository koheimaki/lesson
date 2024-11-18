package com.example.taskmanagerapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.taskmanagerapp.model.Label;

@Repository
public interface LabelRepository extends JpaRepository<Label, Long> {

}
