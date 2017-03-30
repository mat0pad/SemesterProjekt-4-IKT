package com.sem4ikt.uni.recipefinderchatbot.model.spoonacular;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by mathiaslykkepedersen on 30/03/2017.
 */

public class IngredientModel {

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("include")
    @Expose
    private Boolean include;

    @SerializedName("image")
    @Expose
    private String image;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getInclude() {
        return include;
    }

    public void setInclude(Boolean include) {
        this.include = include;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
