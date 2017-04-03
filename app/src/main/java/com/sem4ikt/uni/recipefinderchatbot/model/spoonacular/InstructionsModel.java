package com.sem4ikt.uni.recipefinderchatbot.model.spoonacular;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by anton on 09-03-2017.
 */


public class InstructionsModel {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("steps")
    @Expose
    private List<StepModel> steps = null;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<StepModel> getSteps() {
        return steps;
    }

    public void setSteps(List<StepModel> steps) {
        this.steps = steps;
    }

}
