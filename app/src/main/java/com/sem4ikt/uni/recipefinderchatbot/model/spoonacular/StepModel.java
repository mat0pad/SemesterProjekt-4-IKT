package com.sem4ikt.uni.recipefinderchatbot.model.spoonacular;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by anton on 09-03-2017.
 */

public class StepModel {

    @SerializedName("number")
    @Expose
    private Integer number;
    @SerializedName("step")
    @Expose
    private String step;
    @SerializedName("ingredients")
    @Expose
    private List<SummaryModel> ingredients = null;
    @SerializedName("equipment")
    @Expose
    private List<SummaryModel> equipment = null;

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getStep() {
        return step;
    }

    public void setStep(String step) {
        this.step = step;
    }

    public List<SummaryModel> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<SummaryModel> ingredients) {
        this.ingredients = ingredients;
    }

    public List<SummaryModel> getEquipment() {
        return equipment;
    }

    public void setEquipment(List<SummaryModel> equipment) {
        this.equipment = equipment;
    }

}
