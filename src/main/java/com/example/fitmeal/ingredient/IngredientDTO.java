package com.example.fitmeal.ingredient;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class IngredientDTO {
    // @NotBlank(message = "材料名を入力してください")
    // @Size(min = 1, max = 50, message = "材料名は1文字以上50文字以内で入力してください")
    private String name;

    // @NotBlank(message = "量を入力してください")
    // @Size(min = 1, max = 50, message = "量は1文字以上50文字以内で入力してください")
    private String amount;

    public IngredientDTO() {
    }

    public IngredientDTO(String name, String amount) {
        this.name = name;
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

}