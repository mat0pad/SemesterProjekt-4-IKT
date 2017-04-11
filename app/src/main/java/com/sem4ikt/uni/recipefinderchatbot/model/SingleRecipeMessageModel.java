package com.sem4ikt.uni.recipefinderchatbot.model;

/**
 * Created by mathiaslykkepedersen on 06/04/2017.
 */

public class SingleRecipeMessageModel extends MessageModel {

    public String image;
    public int id;

    public SingleRecipeMessageModel(String msg, int direction, String img, int id) {
        super(msg, direction, TYPE.SINGLE_RECIPE);

        this.image = img;
        this.id = id;
    }
}
