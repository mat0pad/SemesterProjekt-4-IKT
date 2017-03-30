package com.sem4ikt.uni.recipefinderchatbot.model.spoonacular;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by mathiaslykkepedersen on 30/03/2017.
 */

public class RandomRecipeModel {

    @SerializedName("recipes")
    @Expose
    private List<RecipeModel> recipesList;

    public List<RecipeModel> getRecipesList() {
        return recipesList;
    }

    public void setRecipesList(List<RecipeModel> recipesList) {
        this.recipesList = recipesList;
    }
}
