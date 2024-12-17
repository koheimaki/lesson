package com.example.chapter03test.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.chapter03test.exception.InvalidFileException;
import com.example.chapter03test.form.PostForm;
import com.example.chapter03test.model.CustomUserDetails;
import com.example.chapter03test.model.Post;
import com.example.chapter03test.model.User;
import com.example.chapter03test.service.PostService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/general")
public class GeneralController {

    @Autowired
    private PostService postService;

    private static String UPLOADED_FOLDER = "uploads/images/";

    @GetMapping("/posts")
    public String listPosts(Model model) {
        model.addAttribute("posts", postService.findAllPosts());
        model.addAttribute("postForm", new PostForm());
        return "postsList";
    }

    @PostMapping("/posts")
    public String addPost(
            @Valid PostForm postForm,
            BindingResult result,
            @AuthenticationPrincipal CustomUserDetails userDetails,
            RedirectAttributes redirectAttributes,
            Model model) {

        if (result.hasErrors()) {
            model.addAttribute("posts", postService.findAllPosts());
            return "postsList";
        }

        User user = userDetails.getUser();
        MultipartFile image = postForm.getImage();
        if (!image.isEmpty()) {
            String filename = image.getOriginalFilename();
            String extension = filename != null ? filename.substring(filename.lastIndexOf(".")).toLowerCase() : "";
            if (!extension.equals(".png")) {
                throw new InvalidFileException("Only .png files are allowed.");
            }
            if (image.getSize() > 2 * 1024 * 1024) { // 2MB
                throw new InvalidFileException("File size must be less than 2MB.");
            }
            try {
                // ファイル名をPOSTのIDと拡張子で構成
                Post post = postService.createPost(postForm.getContent(), null, user); // 一時的にimageUrlをnullで保存
                String newFilename = post.getId() + extension;
                Path path = Paths.get(UPLOADED_FOLDER + newFilename);
                Files.copy(image.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
                postService.updatePostImage(post.getId(), "/uploads/images/" + newFilename);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            postService.createPost(postForm.getContent(), null, user);
        }

        return "redirect:/general/posts";
    }
}