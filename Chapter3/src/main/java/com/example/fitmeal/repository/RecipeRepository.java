package com.example.fitmeal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.fitmeal.entity.Recipe;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Long>{
    // 一番新規で登録されたレシピを取得（単一のレシピ）
    public Recipe findTop1ByOrderByCreatedAtDesc();

    // 指定したIDを除外してレシピを取得
    public List<Recipe> findByIdNot(Long id);
    
}
