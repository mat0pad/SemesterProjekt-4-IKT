package com.sem4ikt.uni.recipefinderchatbot.database.Interface;

import com.google.android.gms.tasks.Task;
import com.sem4ikt.uni.recipefinderchatbot.model.firebasedb.User;
import com.sem4ikt.uni.recipefinderchatbot.model.spoonacular.MealPlanModel;
import com.sem4ikt.uni.recipefinderchatbot.model.spoonacular.RecipeModel;

import java.util.List;

/**
 * Created by mathiaslykkepedersen on 16/03/2017.
 */

public interface IFirebaseDBRepository {

    interface IUserRepository {

        void addUser(User user);

        void removeUser();

        void getUser();

    }

    interface IRecipeRepository {

        void addRecipe(RecipeModel recipe);

        void removeRecipe(RecipeModel recipe);

        List<RecipeModel> getRecipes();
    }

    interface IMealplanRepository{

        void addMealPlan(MealPlanModel mealplan);

        void removeMealplan();

        MealPlanModel getMealplan();
    }

}
