// package com.example.fitmeal.dev;

// import org.springframework.stereotype.Controller;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.RequestMapping;

// @Controller
// @RequestMapping("/dev")
// public class devRecipeController {
//     //レシピ一覧(admin)
//     @RequestMapping("/recipes/admin")
//     public String Adminrecipes() {
//         return "layout/recipes/admin/recipes";
//     }
//     //レシピ一覧(user)
//     @RequestMapping("/recipes")
//     public String recipes() {
//         return "layout/recipes/user/recipes";
//     }
//     //レシピ詳細
//     @GetMapping("/recipes/detail")
//     public String detailRecipes() {
//         return "layout/recipes/recipes_detail";
//     }
//     //レシピ新規登録
//     @GetMapping("/recipes/new")
//     public String newRecipes() {
//         return "layout/recipes/recipes_form";
//     }
//     //レシピ編集
//     @GetMapping("/recipes/edit")
//     public String editRecipes() {
//         return "layout/recipes/recipes_edit";
//     }
// }