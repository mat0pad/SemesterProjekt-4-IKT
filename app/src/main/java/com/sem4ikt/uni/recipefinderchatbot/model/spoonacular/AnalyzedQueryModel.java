package com.sem4ikt.uni.recipefinderchatbot.model.spoonacular;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by mathiaslykkepedersen on 30/03/2017.
 */

public class AnalyzedQueryModel {

    @SerializedName("ingredients")
    @Expose
    private List<IngredientModel> ingredients = null;
    @SerializedName("dishes")
    @Expose
    private List<DishModel> dishes = null;
    @SerializedName("modifiers")
    @Expose
    private List<ModifierModel> modifiers = null;
    @SerializedName("cuisines")
    @Expose
    private List<ModifierModel> cuisines = null;

    public List<IngredientModel> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<IngredientModel> ingredients) {
        this.ingredients = ingredients;
    }

    public List<DishModel> getDishes() {
        return dishes;
    }

    public void setDishes(List<DishModel> dishes) {
        this.dishes = dishes;
    }

    public List<ModifierModel> getModifiers() {
        return modifiers;
    }

    public void setModifiers(List<ModifierModel> modifiers) {
        this.modifiers = modifiers;
    }

    public List<ModifierModel> getCuisines() {
        return cuisines;
    }

    public void setCuisines(List<ModifierModel> cuisines) {
        this.cuisines = cuisines;
    }

}