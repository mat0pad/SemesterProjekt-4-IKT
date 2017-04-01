package com.sem4ikt.uni.recipefinderchatbot.database;

import com.sem4ikt.uni.recipefinderchatbot.model.spoonacular.MealPlanModel;
import com.sem4ikt.uni.recipefinderchatbot.model.spoonacular.RecipeModel;

/**
 * Created by kaspe on 30-03-2017.
 */

public interface IDatabaseApi {
    boolean saveUser(String userName, String UID);
    boolean SaveRecipe(RecipeModel recipe,String uId);
    boolean SaveMealplan(MealPlanModel mealPlan, String UID);
    boolean SaveCalories();
    String getUser(String uid);
    boolean GetRecipe();
    boolean GetMealplan();
    boolean GetCalories();
    boolean DeleteRecipe();
    boolean DeleteMealplan();
    boolean DeleteCalories();
}
