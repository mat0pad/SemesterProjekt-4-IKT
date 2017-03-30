package com.sem4ikt.uni.recipefinderchatbot.model;

/**
 * Created by mathiaslykkepedersen on 30/03/2017.
 */

public class MessageModel {

    public String message;
    public String image;
    public int direction;
    public int id;
    public TYPE type;

    public MessageModel(String msg, int direction) {
        message = msg;
        image = null;
        id = -1;
        this.direction = direction;
        type = TYPE.NORMAL;
    }

    public MessageModel(String msg, int direction, String img, int id, TYPE type) {
        message = msg;
        image = img;
        this.id = id;
        this.direction = direction;
        this.type = type;
    }

    public enum TYPE {NORMAL, SINGLE_RECIPE, MORE_RECIPES}
}
