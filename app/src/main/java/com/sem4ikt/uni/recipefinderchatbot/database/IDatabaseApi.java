package com.sem4ikt.uni.recipefinderchatbot.database;

/**
 * Created by kaspe on 30-03-2017.
 */

public interface IDatabaseApi {
    boolean SaveRecipe();
    boolean SaveMealplan();
    boolean SaveCalories();
    boolean GetRecipe();
    boolean GetMealplan();
    boolean GetCalories();
    boolean DeleteRecipe();
    boolean DeleteMealplan();
    boolean DeleteCalories();
}
