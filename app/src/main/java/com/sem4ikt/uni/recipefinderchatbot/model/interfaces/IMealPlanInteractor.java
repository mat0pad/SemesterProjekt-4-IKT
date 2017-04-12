package com.sem4ikt.uni.recipefinderchatbot.model.interfaces;

import com.sem4ikt.uni.recipefinderchatbot.model.spoonacular.MealPlanDayModel;
import com.sem4ikt.uni.recipefinderchatbot.model.spoonacular.MealPlanWeekModel;

/**
 * Created by kaspe on 12-04-2017.
 */

public interface IMealPlanInteractor {
    void getmealDayPlan(String diet,int calories,String without);
    void getmealWeekPlan(String diet,int calories,String without);
}
