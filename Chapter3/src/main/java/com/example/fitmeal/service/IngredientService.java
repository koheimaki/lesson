package com.example.fitmeal.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.fitmeal.entity.Ingredient;
import com.example.fitmeal.repository.IngredientRepository;

@Service
public class IngredientService {
    @Autowired
    private IngredientRepository ingredientRepository;

    public List<Ingredient> findByRecipeId(Long recipeId) {
        List<Ingredient> ingredients = ingredientRepository.findByRecipeId(recipeId);
        return ingredients;
    }

    public Ingredient save(Ingredient ingredient){
        return ingredientRepository.save(ingredient);
    }
}
