package com.example.taskmanagerapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.taskmanagerapp.model.Status;

@Repository
public interface StatusRepository extends JpaRepository<Status, Long> {

}
