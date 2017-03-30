package com.sem4ikt.uni.recipefinderchatbot.database;

/**
 * Created by kaspe on 30-03-2017.
 */

public interface IDatabaseApi {
    boolean saveUser();
    boolean SaveRecipe();
    boolean SaveMealplan();
    boolean SaveCalories();
    boolean getUser();
    boolean GetRecipe();
    boolean GetMealplan();
    boolean GetCalories();
    boolean DeleteRecipe();
    boolean DeleteMealplan();
    boolean DeleteCalories();
}
