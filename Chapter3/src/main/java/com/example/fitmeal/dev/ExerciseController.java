package com.example.fitmeal.dev;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/dev")
public class ExerciseController {
    @GetMapping("/exercises")
    public String exercises() {
        return "layout/exercises/exercises";
    }

    @GetMapping("/exercises/edit")
    public String edit() {
        return "layout/exercises/exercises_edit";
    }

    @GetMapping("/exercises/form")
    public String form() {
        return "layout/exercises/exercises_form";
    }

    
}
