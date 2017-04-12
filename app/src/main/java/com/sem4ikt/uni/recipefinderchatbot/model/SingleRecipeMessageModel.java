package com.sem4ikt.uni.recipefinderchatbot.model;

/**
 * Created by mathiaslykkepedersen on 06/04/2017.
 */

public class SingleRecipeMessageModel extends MessageModel {

    private static String BASE_URL = "https://spoonacular.com/recipeImages/";

    public String image;
    public int id;

    public SingleRecipeMessageModel(String msg, int direction, String img, int id) {
        super(msg, direction, TYPE.SINGLE_RECIPE);

        if (!img.contains("https"))
            this.image = BASE_URL + img;
        else
            this.image = img;

        this.id = id;
    }
}
