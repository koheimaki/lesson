package com.example.fitmeal.dev;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/dev")
public class categoriesController {
    @RequestMapping("/categories")
    public String categories() {
        return "layout/categories/categories";
    }

    @RequestMapping("/categories/new")
    //新規登録画面
    public String categoriesNew(){
        return "layout/categories/category_form";
    }

    @RequestMapping("/categories/edit")
    //編集画面
    public String categoriesEdit(){
        return "layout/categories/category_edit";
    }

}
