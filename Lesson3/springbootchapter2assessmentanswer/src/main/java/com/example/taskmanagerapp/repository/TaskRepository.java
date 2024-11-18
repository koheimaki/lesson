package com.example.taskmanagerapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.taskmanagerapp.model.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    @Query(value = "SELECT * FROM tasks WHERE name = :name or status_id = :statusId or user_id = :userId", nativeQuery = true)
    public List<Task> findByParams(@Param("name") String name, @Param("statusId") Long statusId,
            @Param("userId") Long userId);
}
