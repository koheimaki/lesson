package com.example.fitmeal.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.fitmeal.entity.Category;
import com.example.fitmeal.repository.CategoryRepository;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoriesRepository;
    
    //全てのカテゴリー習得
    // public List<Category>findAllCategories(){
    //     return categoriesRepository.findAll()
    //     .stream()
    //     .filter(category -> !category.isDeleted()) // isDeletedがfalseのデータのみ取得
    //     .collect(Collectors.toList());
    // }
    public List<Category> findAllCategories() {
        return categoriesRepository.findAllActiveCategories();
    }

     // IDでカテゴリーを取得
    public Category findById(Long id) {
        return categoriesRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid category ID: " + id));
    }
    // カテゴリーを保存（新規または更新）
    public void saveCategory(Category category) {
        resetSequence(); // 保存後にシーケンスをリセット
        categoriesRepository.save(category);
    }

    //シーケンスのリセット
    public void resetSequence() {
        categoriesRepository.resetSequence();
    }
    // // 追加: DTOリストを返すメソッド
    // public List<CategoriesDTO> findAllCategories() {
    //     return categoriesRepository.findAll().stream()
    //             .map(this::convertToDTO) // エンティティをDTOに変換
    //             .collect(Collectors.toList());
    // }

    // // 追加: DTOでカテゴリーを取得
    // public CategoriesDTO findCategoryById(Long id) {
    //     Category category = categoriesRepository.findById(id)
    //             .orElseThrow(() -> new IllegalArgumentException("Invalid category ID: " + id));
    //     return convertToDTO(category);
    // }

    // // 追加: DTOをエンティティに変換して保存
    // public void saveCategory(CategoriesDTO categoriesDTO) {
    //     Category category = convertToEntity(categoriesDTO);
    //     categoriesRepository.save(category);
    // }

    // // 追加: エンティティ → DTO変換メソッド
    // private CategoriesDTO convertToDTO(Category category) {
    //     return new CategoriesDTO(
    //             category.getId(),
    //             category.getName(),
    //             category.getCreatedAt(),
    //             category.getUpdatedAt(),
    //             category.isDeleted()
    //     );
    // }

    // // 追加: DTO → エンティティ変換メソッド
    // private Category convertToEntity(CategoriesDTO categoriesDTO) {
    //     Category category = new Category();
    //     category.setId(categoriesDTO.getId());
    //     category.setName(categoriesDTO.getName());
    //     category.setCreatedAt(categoriesDTO.getCreatedAt());
    //     category.setUpdatedAt(categoriesDTO.getUpdatedAt());
    //     category.setDeleted(categoriesDTO.isDeleted());
    //     return category;
    // }

}
