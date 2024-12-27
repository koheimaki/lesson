package com.example.fitmeal.recipe;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.fitmeal.entity.Cooking;

@Repository
public interface CookingRepository extends JpaRepository<Cooking, Long> {
    List<Cooking> findByRecipeId(Long recipeId);
}
