package com.example.fitmeal.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.fitmeal.entity.Cooking;
import com.example.fitmeal.entity.Ingredient;
import com.example.fitmeal.entity.Recipe;
import com.example.fitmeal.entity.User;
import com.example.fitmeal.recipe.RecipeDTO;
import com.example.fitmeal.repository.RecipeRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class RecipeService {
    // ファイルの保存先
    private static String UPLOADED_FOLDER = "uploads/images/recipe/";
    @Autowired
    private RecipeRepository recipeRepository;
    @Autowired
    private UserService userService;
    // @Autowired
    // private CategoryService categoryService;
    @Autowired
    private IngredientService ingredientService;
    @Autowired
    private CookingService cookingService;
    //1番新規で登録されたレシピを取得
    public Recipe findTop1ByOderReciepe() {
        return recipeRepository.findTop1ByOrderByCreatedAtDesc();
    }

    //1番新規以外のレシピを取得
    public List<Recipe> getRecipesExceptLatest() {
        // 最新のレシピを取得
        Recipe latestRecipe = recipeRepository.findTop1ByOrderByCreatedAtDesc();

        // 最新のレシピIDを除外して他のレシピを取得
        if (latestRecipe != null) {
            return recipeRepository.findByIdNot(latestRecipe.getId());
        }
        return recipeRepository.findAll(); // 最新のレシピがない場合はすべてのレシピを返す
    }

    //レシピIDでレシピを取得(レシピ詳細)
    public Recipe findByRecipeId(Long id) {
        Recipe recipe = recipeRepository.findById(id).orElseThrow(() -> new EntityNotFoundException());
        return recipe;
    }
    //新規保存・更新
    @Transactional
    public void save(RecipeDTO recipeDTO) {
        MultipartFile imageFile = recipeDTO.getImageFile();

        // 画像ファイルが存在する場合、保存処理を行う
        String imageUrl = null;
        // 更新の場合 現在の画像の名前をいれる
        if(recipeDTO.getId() != null) {
            imageUrl = findByRecipeId(recipeDTO.getId()).getImageUrl();
        }
        if (imageFile != null && !imageFile.isEmpty()) {
            String originalFilename = imageFile.getOriginalFilename();
            String fileExtension = originalFilename != null ? originalFilename.substring(originalFilename.lastIndexOf(".") + 1).toLowerCase() : "";
            // 拡張子がPNGでない場合はエラーメッセージを返す
            if (!fileExtension.equals("png")) {
                throw new IllegalArgumentException("許可されていないファイル形式です");
            }

            try {
                // 画像保存先の決定（ファイル名を含む）
                String fileName = recipeDTO.getName() + "_" + System.currentTimeMillis() + ".png"; // ファイル名を動的に生成（例: レシピ名 + 現在時刻）
                Path path = Paths.get(UPLOADED_FOLDER + fileName);

                // 画像ファイルの保存
                Files.copy(imageFile.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);

                imageUrl = UPLOADED_FOLDER + fileName;

            } catch (IOException e) {
                e.printStackTrace();
                throw new RuntimeException("画像ファイルの保存中にエラーが発生しました");
            }
        }

        User user = userService.findById(1L);
        Recipe recipe = new Recipe(
                                    recipeDTO.getId(),
                                    recipeDTO.getName(),
                                    imageUrl,
                                    recipeDTO.getCookingTime(),
                                    recipeDTO.getCalorie(),
                                    user,
                                    recipeDTO.getCategory());
        // 材料テーブルへの保存
        Recipe returnRecipe = recipeRepository.save(recipe);
        recipeDTO.getIngredients().stream().forEach(In->{In.setRecipe(returnRecipe); ingredientService.save(In);});
        // 手順テーブルの保存
        recipeDTO.getCookings().stream().forEach(Co->{Co.setRecipe(returnRecipe); cookingService.save(Co);});
    }

    //レシピ編集データ受け渡し
    public RecipeDTO findByRecipeIdDTO(Long recipeId) {
        Recipe recipe = findByRecipeId(recipeId);
        List<Ingredient> ingredients = ingredientService.findByRecipeId(recipeId);
        List<Cooking> cookings = cookingService.findByRecipeId(recipeId);
        RecipeDTO dataRecipe = new RecipeDTO();
        dataRecipe.setId(recipe.getId());
        dataRecipe.setName(recipe.getName());
        dataRecipe.setImageFile(null);
        dataRecipe.setCookingTime(recipe.getCookingTime());
        dataRecipe.setCalorie(recipe.getCalorie());
        dataRecipe.setCategory(recipe.getCategory());
        dataRecipe.setUser(recipe.getUser());
        dataRecipe.setIngredients(ingredients);
        dataRecipe.setCookings(cookings);
        return dataRecipe;
    }

    //レシピ削除
    public void deleteRecipeById(Long id) {
        Recipe recipe = recipeRepository.findById(id).orElseThrow(() -> new EntityNotFoundException());
        recipe.setDeleted(true);
        recipeRepository.save(recipe);
    }
}
