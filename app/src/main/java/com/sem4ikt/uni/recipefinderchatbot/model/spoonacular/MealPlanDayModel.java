package com.sem4ikt.uni.recipefinderchatbot.model.spoonacular;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by anton on 09-03-2017.
 */
public class MealPlanDayModel {

    @SerializedName("meals")
    @Expose
    private List<RecipeModel> RecipeModels = null;
    @SerializedName("nutrients")
    @Expose
    private NutrientModel nutrientModel;

    public List<RecipeModel> getRecipeModels() {return  RecipeModels;}

    public void setRecipeModels(List<RecipeModel> meals) {
        this.RecipeModels = meals;
    }

    public NutrientModel getNutrients() {
        return nutrientModel;
    }

    public void setNutrients(NutrientModel nutrientModel) {
        this.nutrientModel = nutrientModel;
    }

}