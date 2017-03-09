package com.sem4ikt.uni.recipefinderchatbot.model.spoonacular;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by mathiaslykkepedersen on 09/03/2017.
 */

public class IngredientSubstituteModel {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("ingredient")
    @Expose
    private String ingredient;
    @SerializedName("substitutes")
    @Expose
    private List<String> substitutes = null;
    @SerializedName("message")
    @Expose
    private String message;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getIngredient() {
        return ingredient;
    }

    public void setIngredient(String ingredient) {
        this.ingredient = ingredient;
    }

    public List<String> getSubstitutes() {
        return substitutes;
    }

    public void setSubstitutes(List<String> substitutes) {
        this.substitutes = substitutes;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}

