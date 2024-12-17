package com.example.chapter03test.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.chapter03test.model.Post;
import com.example.chapter03test.service.PostService;

@RestController
public class HomeRestController {

    @Autowired
    private PostService postService;

    @GetMapping("/api")
    public Post getNewestPosts() {
        Optional<Post> post = postService.findNewestPosts();
        return post.isPresent() ? post.get() : null;
    }
}

