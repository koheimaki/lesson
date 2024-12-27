package com.example.fitmeal.cooking;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class CookingDTO {
    // @NotBlank(message = "手順番号が抜けています")
    private Integer stepNumber;

    // @NotBlank(message = "調理説明を入力してください")
    // @Size(min = 1, max = 300, message = "調理説明は1文字以上300字以内で入力してください")
    private String description;

    public CookingDTO() {
    }

    public CookingDTO(Integer stepNumber, String description) {
        this.stepNumber = stepNumber;
        this.description = description;
    }

    public Integer getStepNumber() {
        return stepNumber;
    }

    public void setStepNumber(Integer stepNumber) {
        this.stepNumber = stepNumber;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    
}
