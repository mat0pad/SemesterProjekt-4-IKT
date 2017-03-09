package com.sem4ikt.uni.recipefinderchatbot.model.spoonacular;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.sem4ikt.uni.recipefinderchatbot.model.RecipeModel;
/**
 * Created by anton on 09-03-2017.
 */
public class MealPlanModel {

    @SerializedName("meals")
    @Expose
    private List<RecipeModel> RecipeModels = null;
    @SerializedName("nutrients")
    @Expose
    private NutrientModel nutrientModel;

    public List<RecipeModel> getRecipeModels() {
        return  RecipeModels;
    }

    public void setRecipeModel(List<RecipeModel> meals) {
        this.RecipeModels = RecipeModels;
    }

    public NutrientModel getNutrients() {
        return nutrientModel;
    }

    public void setNutrients(NutrientModel nutrientModel) {
        this.nutrientModel = nutrientModel;
    }

}