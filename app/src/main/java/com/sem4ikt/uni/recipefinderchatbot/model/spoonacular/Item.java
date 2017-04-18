package com.sem4ikt.uni.recipefinderchatbot.model.spoonacular;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by anton on 13-04-2017.
 */

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
