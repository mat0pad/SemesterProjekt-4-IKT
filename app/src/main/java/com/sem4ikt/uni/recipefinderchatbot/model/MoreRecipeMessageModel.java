package com.sem4ikt.uni.recipefinderchatbot.model;

/**
 * Created by mathiaslykkepedersen on 06/04/2017.
 */

public class MoreRecipeMessageModel extends MessageModel {

    public Object obj;
    public String image;

    public MoreRecipeMessageModel(String msg, int direction, String img, Object obj, TYPE type) {
        super(msg, direction, type);

        this.image = img;
        this.obj = obj;
    }
}
