package com.example.fitmeal.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.fitmeal.entity.Cooking;
import com.example.fitmeal.entity.Ingredient;
import com.example.fitmeal.recipe.CookingRepository;

@Service
public class CookingService {
    @Autowired
    private CookingRepository cookingRepository;
    //レシピIDで作り方手順を取得(詳細)
    public List<Cooking> findByRecipeId(Long recipeId) {
        List<Cooking> cookings = cookingRepository.findByRecipeId(recipeId);
        return cookings;
    }

    public Cooking save(Cooking cooking){
        return cookingRepository.save(cooking);
    }
}
