package com.example.fitmeal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.fitmeal.entity.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long>{
    @Query("SELECT c FROM Category c WHERE c.isDeleted = false ORDER BY c.id ASC")
    List<Category> findAllActiveCategories();
    @Query(value = "SELECT setval('categories_id_seq', (SELECT COALESCE(MAX(id), 0) FROM categories))", nativeQuery = true)
    public void resetSequence();

}
