package com.sem4ikt.uni.recipefinderchatbot.database;

import com.sem4ikt.uni.recipefinderchatbot.model.spoonacular.MealPlanModel;

/**
 * Created by kaspe on 30-03-2017.
 */

public interface IDatabaseApi {
    boolean SaveUser(String userName, String UID);
    boolean SaveRecipe();
    boolean SaveMealplan(MealPlanModel mealPlan,String UID);
    boolean SaveCalories();
    String getUser(String UID);
    boolean GetRecipe();
    boolean GetMealplan();
    boolean GetCalories();
    boolean DeleteRecipe();
    boolean DeleteMealplan();
    boolean DeleteCalories();
}
