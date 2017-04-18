package com.sem4ikt.uni.recipefinderchatbot.database.Interface;

import com.sem4ikt.uni.recipefinderchatbot.model.firebasedb.User;
import com.sem4ikt.uni.recipefinderchatbot.model.spoonacular.MealPlanDayModel;
import com.sem4ikt.uni.recipefinderchatbot.model.spoonacular.RecipeModel;
import com.sem4ikt.uni.recipefinderchatbot.model.spoonacular.RecipesModel;

/**
 * Created by mathiaslykkepedersen on 16/03/2017.
 */

public interface IFirebaseDBInteractors {

    interface IUserInteractor {

        void addUser(User user);

        void removeUser();

        void getUser(ICallbackUser callback);

    }

    interface IRecipesInteractor {

        void addRecipe(RecipesModel recipe);

        void removeRecipe(RecipesModel recipe);

        void getRecipes();

        void searchRecipesbyTitle(String Query);
    }

    interface IMealplanInteractor{

        void addMealPlan(MealPlanDayModel mealplan);


        void removeMealplan();

        void getMealplan(ICallbackMealplan callback);
    }

    interface  IRecipeInteractor {

        void addRecipe(RecipeModel recipe);

        void removeRecipe(RecipeModel recipe);

        void getRecipe(ICallbackRecipe callback);


    }

}
