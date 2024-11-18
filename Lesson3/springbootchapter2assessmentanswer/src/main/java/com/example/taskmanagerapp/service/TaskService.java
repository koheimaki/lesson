package com.example.taskmanagerapp.service;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.taskmanagerapp.model.Label;
import com.example.taskmanagerapp.model.Task;
import com.example.taskmanagerapp.repository.TaskRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class TaskService {
    @Autowired
    private TaskRepository taskRepository;

    public List<Task> findAllTasks() {
        return taskRepository.findAll();
    }

    public Task findTaskById(Long id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Task not found with id: " + id));
        return task;
    }

    public Task updateTask(Task task) {
        return taskRepository.save(task);
    }

    public Task deleteTask(Task task) {
        task.setDeleted(true);
        return taskRepository.save(task);
    }

    public List<Task> findTaskByParams(String name, Long statusId, Long userId) {
        if (name == null && statusId == null && userId == null) {
            return taskRepository.findAll();
        }
        List<Task> tasks = taskRepository.findByParams(name, statusId, userId);
        return tasks;
    }

    @Transactional
    public Task addTask(Task task, Set<Label> labelSet) {
        task.setLabels(labelSet);
        return taskRepository.save(task);
    }
}
