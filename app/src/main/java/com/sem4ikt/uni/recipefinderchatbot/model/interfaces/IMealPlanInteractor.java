package com.sem4ikt.uni.recipefinderchatbot.model.interfaces;

import com.sem4ikt.uni.recipefinderchatbot.model.spoonacular.MealPlanModel;

/**
 * Created by kaspe on 12-04-2017.
 */

public interface IMealPlanInteractor {
    MealPlanModel getmealPlan(String diet,int calories,String time,String without);
}
