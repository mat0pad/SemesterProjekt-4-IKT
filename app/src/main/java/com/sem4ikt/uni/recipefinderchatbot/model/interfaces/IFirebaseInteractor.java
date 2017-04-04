package com.sem4ikt.uni.recipefinderchatbot.model.interfaces;

import com.google.android.gms.tasks.Task;
import com.sem4ikt.uni.recipefinderchatbot.model.firebasedb.User;
import com.sem4ikt.uni.recipefinderchatbot.model.spoonacular.MealPlanModel;
import com.sem4ikt.uni.recipefinderchatbot.model.spoonacular.RecipeModel;

import java.util.List;

/**
 * Created by anton on 31-03-2017.
 */

public interface IFirebaseInteractor {


    void addUser(User user);

    void removeUser();

    void getUser();

    void removeRecipe(RecipeModel recipe);

    void addRecipe(RecipeModel recipe);

    List<RecipeModel> getRecipes();

    void addMealplan(MealPlanModel mealplan);

    void removeMealplan();

    MealPlanModel getMealplan();

    void callback(User user);

    void Test();


}
