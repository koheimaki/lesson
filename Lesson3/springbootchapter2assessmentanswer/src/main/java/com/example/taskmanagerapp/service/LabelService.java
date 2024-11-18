package com.example.taskmanagerapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.taskmanagerapp.model.Label;
import com.example.taskmanagerapp.repository.LabelRepository;

@Service
public class LabelService {
    @Autowired
    private LabelRepository labelRepository;

    public List<Label> findAllLabels() {
        return labelRepository.findAll();
    }
}
