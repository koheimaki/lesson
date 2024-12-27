package com.example.fitmeal.recipe;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.example.fitmeal.entity.Category;
import com.example.fitmeal.entity.Cooking;
import com.example.fitmeal.entity.Ingredient;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.ArrayList;
import java.util.List;

import com.example.fitmeal.entity.Category;
import com.example.fitmeal.entity.Cooking;
import com.example.fitmeal.entity.Ingredient;
import com.example.fitmeal.entity.User;

public class RecipeDTO {
    private Long id;

    @NotBlank(message = "レシピ名を入力してください")
    @Size(min = 1, max = 50, message ="レシピ名は1文字以上50文字以内で入力してください")
    private String name;

    private MultipartFile imageFile;

    private Integer cookingTime;

    @NotNull(message = "カロリーを入力してください")
    private Integer calorie;

    
    private User user;

    @NotNull(message = "カテゴリを選択してください")
    private Category category;

    private List<@Valid Ingredient> ingredients  = new ArrayList<>();

    private List<@Valid Cooking> cookings  = new ArrayList<>();

    public RecipeDTO() {
    }

    public RecipeDTO(String name, MultipartFile imageFile, Integer cookingTime, Integer calorie, User user,
            Category category, List<Ingredient> ingredients, List<Cooking> cookings) {
        this.name = name;
        this.imageFile = imageFile;
        this.cookingTime = cookingTime;
        this.calorie = calorie;
        this.user = user;
        this.category = category;
        this.ingredients = ingredients;
        this.cookings = cookings;
    }

    public RecipeDTO(Long id, String name, MultipartFile imageFile, Integer cookingTime, Integer calorie, User user,
            Category category, List<Ingredient> ingredients, List<Cooking> cookings) {
        this.id = id;
        this.name = name;
        this.imageFile = imageFile;
        this.cookingTime = cookingTime;
        this.calorie = calorie;
        this.user = user;
        this.category = category;
        this.ingredients = ingredients;
        this.cookings = cookings;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MultipartFile getImageFile() {
        return imageFile;
    }

    public void setImageFile(MultipartFile imageFile) {
        this.imageFile = imageFile;
    }

    public Integer getCookingTime() {
        return cookingTime;
    }

    public void setCookingTime(Integer cookingTime) {
        this.cookingTime = cookingTime;
    }

    public Integer getCalorie() {
        return calorie;
    }

    public void setCalorie(Integer calorie) {
        this.calorie = calorie;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public List<Cooking> getCookings() {
        return cookings;
    }

    public void setCookings(List<Cooking> cookings) {
        this.cookings = cookings;
    }

}