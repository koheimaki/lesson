package com.example.taskmanagerapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.taskmanagerapp.model.Status;
import com.example.taskmanagerapp.repository.StatusRepository;

@Service
public class StatusService {
    @Autowired
    private StatusRepository statusRepository;

    public List<Status> findAllStatus() {
        return statusRepository.findAll();
    }
}
