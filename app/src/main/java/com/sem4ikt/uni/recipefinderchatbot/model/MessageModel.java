package com.sem4ikt.uni.recipefinderchatbot.model;

/**
 * Created by mathiaslykkepedersen on 30/03/2017.
 */

public class MessageModel {

    public String message;
    public int direction;
    public TYPE type;

    public MessageModel(String msg, int direction, TYPE type) {
        message = msg;
        this.direction = direction;
        this.type = type;
    }

    public enum TYPE {NORMAL, SINGLE_RECIPE, MORE_RECIPE_MODEL, MORE_RECIPES_MODEL, MORE_NUTRIENTS_MODEL, MORE_INGREDIENTS_MODEL}
}
