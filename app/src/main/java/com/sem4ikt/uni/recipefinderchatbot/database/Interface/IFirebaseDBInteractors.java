package com.sem4ikt.uni.recipefinderchatbot.database.Interface;

import com.google.android.gms.tasks.Task;
import com.sem4ikt.uni.recipefinderchatbot.model.firebasedb.User;
import com.sem4ikt.uni.recipefinderchatbot.model.spoonacular.MealPlanModel;
import com.sem4ikt.uni.recipefinderchatbot.model.spoonacular.RecipeModel;
import com.sem4ikt.uni.recipefinderchatbot.model.spoonacular.RecipesModel;

import java.util.List;

/**
 * Created by mathiaslykkepedersen on 16/03/2017.
 */

public interface IFirebaseDBInteractors {

    interface IUserInteractor {

        void addUser(User user);

        void removeUser();

        void getUser();

    }

    interface IRecipeInteractor {

        void addRecipe(RecipesModel recipe);

        void removeRecipe(RecipesModel recipe);

        void getRecipes();
    }

    interface IMealplanInteractor{

        void addMealPlan(MealPlanModel mealplan);

        void removeMealplan();

        MealPlanModel getMealplan();
    }

}
