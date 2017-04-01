package com.sem4ikt.uni.recipefinderchatbot.database.FirebaseDB.Interface;

import com.sem4ikt.uni.recipefinderchatbot.database.FirebaseDB.User;
import com.sem4ikt.uni.recipefinderchatbot.model.spoonacular.MealPlanModel;
import com.sem4ikt.uni.recipefinderchatbot.model.spoonacular.RecipeModel;

/**
 * Created by anton on 31-03-2017.
 */

public interface IFirebaseInteractor {


    void addUser(User user);

    void removeUser();

    User getUser();

    void removeRecipe(RecipeModel recipe);

    void addRecipe(RecipeModel recipe);

    RecipeModel getRecipes();

    void addMealplan(MealPlanModel mealplan);

    void removeMealplan();

    MealPlanModel getMealplan();

}
