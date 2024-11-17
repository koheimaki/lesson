package com.example.fukushu2.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.fukushu2.model.User;
import com.example.fukushu2.service.UserService;

import jakarta.persistence.EntityNotFoundException;

@Controller
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/list")
    public String showList(Model model) {
        List<User> users = userService.findAllUsers();
        model.addAttribute("users", users);
        return "user/list";
    }

    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("user", new User());
        return "user/add";
    }

    @PostMapping("/add")
    public String add(User user) {
        userService.saveUser(user);
        return "redirect:/users/list";
    }

    @GetMapping("/edit/{userId}")
    public String editForm(@PathVariable("userId") Long userId, Model model) {
        User user = userService.findUserById(userId)
            .orElseThrow(() -> new EntityNotFoundException("User not found with id " +userId));
        model.addAttribute("user", user);
        return "user/edit";
    }

    @PostMapping("/edit")
    public String edit(User user) {
        userService.updateUser(user);
        return "redirect:/users/list";
    }

    @GetMapping("/delete/{userId}")
    public String delete(@PathVariable("userId") Long id) {
        userService.deleteUserById(id);
        return "redirect:/users/list";
    }
}
