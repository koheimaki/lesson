package com.example.fitmeal.dev;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/dev")
public class devCaloriesController {

    @GetMapping("/calories")
    public String showCaloriesPage() {
        return "/layout/calories/calories";
    }

    @GetMapping("/calories/new")
    public String showCaloriesForm() {
        return "/layout/calories/calorie_form";
    }
    
    @GetMapping("/calories/edit")
    public String showCaloriesEdit() {
        return "/layout/calories/calorie_edit";
    }
}
