package com.example.fitmeal.recipe.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.fitmeal.entity.Category;
import com.example.fitmeal.entity.Cooking;
import com.example.fitmeal.entity.Ingredient;
import com.example.fitmeal.entity.Recipe;
import com.example.fitmeal.recipe.RecipeDTO;
import com.example.fitmeal.service.CategoryService;
import com.example.fitmeal.service.CookingService;
import com.example.fitmeal.service.IngredientService;
import com.example.fitmeal.service.RecipeService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/admin/recipes")
public class AdminRecipeController {
    @Autowired
    private RecipeService recipeService;
    @Autowired
    private IngredientService ingredientService;
    @Autowired
    private CookingService cookingService;
    @Autowired
    private CategoryService categoriesService;
    //Admin
    //レシピ一覧
    @GetMapping("")
    public String recipes(Model model) {
        Recipe newRecipe = recipeService.findTop1ByOderReciepe();
        List<Recipe> recipes = recipeService.getRecipesExceptLatest();
        List<Category> categories = categoriesService.findAllCategories();
        System.out.println(newRecipe.getImageUrl());
        model.addAttribute("categories", categories);
        model.addAttribute("newRecipe", newRecipe);
        model.addAttribute("recipes", recipes);
        return "layout/recipes/admin/recipes";
    }
    //レシピ詳細
    @GetMapping("/detail/{recipeId}")
    public String detailRecipe(@PathVariable("recipeId") Long recipeId, Model model) {
        Recipe recipe = recipeService.findByRecipeId(recipeId);
        String userName = recipe.getUser().getName();
        List<Ingredient> ingredients = ingredientService.findByRecipeId(recipeId);
        List<Cooking> cookings = cookingService.findByRecipeId(recipeId);
        model.addAttribute("recipe", recipe);
        model.addAttribute("ingredients", ingredients);
        model.addAttribute("cookings", cookings);
        model.addAttribute("userName", userName);
        return "layout/recipes/recipes_detail";
    }
    //レシピ新規登録画面
    @GetMapping("/new")
    public String newRecipeForm(Model model) {
        RecipeDTO recipeDTO = new RecipeDTO();
        List<Category> categories = categoriesService.findAllCategories();
        model.addAttribute("recipeDTO", recipeDTO);
        model.addAttribute("categories", categories);
        return "layout/recipes/recipes_form";
    }
    //レシピ新規登録
    @PostMapping("/save")
    public String saveRecipe(@Valid @ModelAttribute RecipeDTO recipeDTO,
                                BindingResult result, Model model) {
        if (result.hasErrors()) {
            List<Category> categories = categoriesService.findAllCategories();
            model.addAttribute("errors", result.getAllErrors());
            model.addAttribute("categories", categories);
            return "layout/recipes/recipes_form";
        }
        recipeService.save(recipeDTO);
        try {
            // 2000ミリ秒スリープ
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();  // スリープが中断された場合のエラーハンドリング
        }
        return "redirect:/admin/recipes";
    }

    //レシピ編集画面
    @GetMapping("/edit/{recipeId}")
    public String editRecipeForm(@PathVariable("recipeId") Long recipeId, Model model) {
        String imageUrl = recipeService.findByRecipeId(recipeId).getImageUrl();
        RecipeDTO recipeDTO = recipeService.findByRecipeIdDTO(recipeId);
        List<Category> categories = categoriesService.findAllCategories();
        model.addAttribute("imageUrl", imageUrl);
        model.addAttribute("recipeDTO", recipeDTO);
        model.addAttribute("categories",categories);
        return "layout/recipes/recipes_edit";
    }
    //レシピ更新
    @PostMapping("/edit/{recipeId}")
    public String updateRecipe(@PathVariable("recipeId") Long recipeId,@Valid @ModelAttribute RecipeDTO recipeDTO,
                                BindingResult result, Model model) {
        if (result.hasErrors()) {
            List<Category> categories = categoriesService.findAllCategories();
            model.addAttribute("errors", result.getAllErrors());
            model.addAttribute("categories", categories);
            return "layout/recipes/recipes_edit";
        }
        System.out.println(recipeDTO.getImageFile());
        recipeService.save(recipeDTO);
        try {
            // 2000ミリ秒スリープ
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();  // スリープが中断された場合のエラーハンドリング
        }
        return "redirect:/admin/recipes";
    }
    //レシピ削除
    @GetMapping("/delete/{recipeId}")
    public String deleteRecipe(@PathVariable("recipeId") Long recipeId) {
        recipeService.deleteRecipeById(recipeId);
        return "redirect:/admin/recipes";
    }
}