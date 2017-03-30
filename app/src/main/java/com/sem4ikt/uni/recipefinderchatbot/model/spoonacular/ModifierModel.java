package com.sem4ikt.uni.recipefinderchatbot.model.spoonacular;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by mathiaslykkepedersen on 30/03/2017.
 */

public class ModifierModel {

    @SerializedName("name")
    @Expose
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
