package com.sem4ikt.uni.recipefinderchatbot.database.Interface;

import com.sem4ikt.uni.recipefinderchatbot.model.firebasedb.User;
import com.sem4ikt.uni.recipefinderchatbot.model.spoonacular.MealPlanDayModel;
import com.sem4ikt.uni.recipefinderchatbot.model.spoonacular.MealPlanWeekModel;
import com.sem4ikt.uni.recipefinderchatbot.model.spoonacular.RecipeModel;
import com.sem4ikt.uni.recipefinderchatbot.model.spoonacular.RecipesModel;

import java.util.Date;

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

        void addMealPlanWeek(MealPlanWeekModel mealplan, Date date);

        void addMealPlanDay(MealPlanDayModel mealplan,Date date);


        void removeMealPlanDay(MealPlanDayModel mealPlanDayModel);

        void removeMealPlanWeek(MealPlanWeekModel mealPlanWeekModel);

        void getMealPlanWeek(ICallbackMealplan callback);

        void getMealPlanDay(ICallbackMealplan callback);
    }

    interface  IRecipeInteractor {

        void addRecipe(RecipeModel recipe);

        void removeRecipe(RecipeModel recipe);

        void getRecipe(ICallbackRecipe callback);


    }

}
