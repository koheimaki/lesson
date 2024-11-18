package com.example.taskmanagerapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.taskmanagerapp.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
