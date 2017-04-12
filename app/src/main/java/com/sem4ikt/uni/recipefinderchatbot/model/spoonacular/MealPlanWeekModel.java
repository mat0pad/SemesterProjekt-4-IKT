package com.sem4ikt.uni.recipefinderchatbot.model.spoonacular;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.json.JSONObject;

import java.util.List;

/**
 * Created by mathiaslykkepedersen on 12/04/2017.
 */

public class MealPlanWeekModel {
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("items")
    @Expose
    private List<Item> items = null;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }


    public class Item {

        @SerializedName("day")
        @Expose
        private Integer day;
        @SerializedName("slot")
        @Expose
        private Integer slot;
        @SerializedName("position")
        @Expose
        private Integer position;
        @SerializedName("type")
        @Expose
        private String type;
        @SerializedName("value")
        @Expose
        private String value;
        @SerializedName("mealPlanId")
        @Expose
        private Integer mealPlanId;

        public Integer getDay() {
            return day;
        }

        public void setDay(Integer day) {
            this.day = day;
        }

        public Integer getSlot() {
            return slot;
        }

        public void setSlot(Integer slot) {
            this.slot = slot;
        }

        public Integer getPosition() {
            return position;
        }

        public void setPosition(Integer position) {
            this.position = position;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public Integer getMealPlanId() {
            return mealPlanId;
        }

        public void setMealPlanId(Integer mealPlanId) {
            this.mealPlanId = mealPlanId;
        }

    }

}