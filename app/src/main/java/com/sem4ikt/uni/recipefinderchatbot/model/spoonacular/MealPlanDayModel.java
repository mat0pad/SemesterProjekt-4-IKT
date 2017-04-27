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
    private List<RecipesModel> RecipeModels = null;
    @SerializedName("nutrients")
    @Expose
    private NutrientModel nutrientModel;

    public List<RecipesModel> getRecipeModels() {return  RecipeModels;}

    public void setRecipeModels(List<RecipesModel> meals) {
        this.RecipeModels = meals;
    }

    public NutrientModel getNutrients() {
        return nutrientModel;
    }

    public void setNutrients(NutrientModel nutrientModel) {
        this.nutrientModel = nutrientModel;
    }

}