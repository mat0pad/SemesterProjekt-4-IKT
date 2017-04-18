package com.sem4ikt.uni.recipefinderchatbot.model.spoonacular;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
/**
 * Created by anton on 09-03-2017.
 */
public class NutrientModel {

    @SerializedName("calories")
    @Expose
    private Double calories;
    @SerializedName("protein")
    @Expose
    private Double protein;
    @SerializedName("fat")
    @Expose
    private Double fat;
    @SerializedName("carbohydrates")
    @Expose
    private Double carbohydrates;

    public Double getCalories() {
        return calories;
    }

    public void setCalories(Double calories) {
        this.calories = calories;
    }

    public Double getProtein() {
        return protein;
    }

    public void setProtein(Double protein) {
        this.protein = protein;
    }

    public Double getFat() {
        return fat;
    }

    public void setFat(Double fat) {
        this.fat = fat;
    }

    public Double getCarbohydrates() {
        return carbohydrates;
    }

    public void setCarbohydrates(Double carbohydrates) {
        this.carbohydrates = carbohydrates;
    }

}
