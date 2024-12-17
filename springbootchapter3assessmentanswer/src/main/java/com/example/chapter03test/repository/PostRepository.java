package com.example.chapter03test.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.chapter03test.model.Post;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findAllByOrderByCreatedAtDesc();

    Post findFirstByOrderByCreatedAtDesc();
}
