package com.example.fitmeal.dev;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/dev")
public class devController {
    @RequestMapping("/")
    public String dev() {
        return "/dev/dev";
    }

    @GetMapping("/login")
    public String test() {
        return "/layout/login/login";
    }

    @GetMapping("/detail")
    public String detail() {
        return "/layout/users/detail";
    }

    @GetMapping("/user_edit")
    public String edit() {
        return "/layout/users/user_edit";
    }

    @GetMapping("/user_form")
    public String form() {
        return "/layout/users/user_form";
    }

    @GetMapping("/user_detail")
    public String user_edit() {
        return "/layout/users/user_detail";
    }

    @GetMapping("/users")
    public String users() {
        return "/layout/users/users";
    }
}
