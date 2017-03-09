package com.sem4ikt.uni.recipefinderchatbot.rest;

import com.sem4ikt.uni.recipefinderchatbot.model.RecipeModel;
import com.sem4ikt.uni.recipefinderchatbot.model.spoonacular.AnswerModel;
import com.sem4ikt.uni.recipefinderchatbot.model.spoonacular.IngredientSubstituteModel;
import com.sem4ikt.uni.recipefinderchatbot.model.spoonacular.IngredientsModel;
import com.sem4ikt.uni.recipefinderchatbot.model.spoonacular.InstructionsModel;
import com.sem4ikt.uni.recipefinderchatbot.model.spoonacular.MealPlanModel;
import com.sem4ikt.uni.recipefinderchatbot.model.spoonacular.NutrientsModel;
import com.sem4ikt.uni.recipefinderchatbot.model.spoonacular.StepModel;
import com.sem4ikt.uni.recipefinderchatbot.model.spoonacular.TextModel;
import com.sem4ikt.uni.recipefinderchatbot.model.spoonacular.RecipesModel;

import java.util.List;
import com.sem4ikt.uni.recipefinderchatbot.model.spoonacular.SummaryModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by mathiaslykkepedersen on 06/03/2017.
 */

public interface ISpoonacularAPI {

    interface ICompute{

        @GET("recipes/{id}/information")
        Call<RecipeModel> getRecipe(@Path("id") int id, @Query("includeNutrition") boolean includeNutrition);


        /**@Params

            diet            : What diet it should be, etc vegetarian
            targetCalories  : Amount of calories mealplan should try to hit
            timeFrame       :Time of the mealplan
            exclude         :What food should be excluded

         **/
        @GET("recipes/mealplans/generate")
        Call<MealPlanModel> getMealPlan(
            @Query("diet") String diet,
            @Query("targetCalories") int targetCalories,
            @Query("timeFrame") String timeFrame,
            @Query("exclude")  String exclude
            );


        /** @Params
            id  : id of recipe to be summarized.
         **/
        @GET("recipes/{id}/summary")
        Call<SummaryModel> summarizeRecipe(
                @Path("id")int id);

        /**
         *
         * @param q     :Nutrion related question
         */

        @GET("recipes/quickAnswer")
        Call<AnswerModel> getQuickAnswer(
                @Query("q") String q
        );

    }

    interface IData{

        @GET("food/jokes/random")
        Call<TextModel> getRandomFoodJoke();

        @GET("food/trivia/random")
        Call<TextModel> getFoodTrivia();

        /**
         *
         * @param id                :the id of recipe
         * @param stepBreakdown     :whether to break down recipe even more
         */
        @GET("recipes/{id}/analyzedInstructions")
        Call<InstructionsModel> getRecipeInstructions(
                @Query("id")  String id,
                @Query("stepBreakdown") boolean stepBreakdown
        );


    }

    interface ISearch {

        /** @Params

         maxCalories : The maximum number of calories the recipe can have.
         minCalories : The minimum number of calories the recipe can have.
         maxCarbs    : The maximum number of carbohydrates in grams the recipe can have.
         minCarbs    : The minimum number of carbohydrates in grams the recipe can have.
         maxFat      : The maximum number of fat in grams the recipe can have.
         minFat      : The minimum number of carbohydrates in grams the recipe can have.
         maxProtein  : The maximum number of proteins in grams the recipe can have.
         minProtein  : The minimum number of proteins in grams the recipe can have.
         number      : How many recipes in result
         offset      : What page to start on
         random      : Should be random?
         **/
        @GET("recipes/findByNutrients")
        Call<NutrientsModel> findByNutrients(
                @Query("maxCalories") int maxCal,
                @Query("minCalories") int minCal,
                @Query("maxCarbs") int maxCarbs,
                @Query("minCarbs") int minCarbs,
                @Query("maxFat") int maxFat,
                @Query("minFat") int minFat,
                @Query("maxProtein") int maxProtein,
                @Query("minProtein") int minProtein,
                @Query("number") int numberOfResults,
                @Query("offset") int pageNr,
                @Query("random") Boolean getRandom
        );

        /**
         *
         * @param ingredients           : Comma-seperated list of ingredients in recipes
         * @param maxNumberOfResults    : Default 5
         * @param fillIngredients       : Extra information about used and missing ingredients
         * @param rank                  : Whether to maximize used ingredients (1) or minimize missing ingredients (2) first.
         * @return
         */
        @GET("recipes/findByIngredients")
        Call<IngredientsModel> findByIngredients(
                @Query("ingredients") String ingredients,
                @Query("number") int maxNumberOfResults,               // default 5
                @Query("fillIngredients") Boolean fillIngredients,
                @Query("ranking") int rank
        );

        @GET("recipes/{id}/similar")
        Call<RecipesModel> findSimilarRecipes(@Path("id") int id);


        @GET("food/ingredients/substitutes")
        Call<IngredientSubstituteModel> findIngredientSubstitutes(@Query("ingredientName") String ingredientName);

        /**
         *
         * @param numberOfRecipes   :Number of recipes to be returned betwheen [1,100]
         * @param tags              :Tags that random recipes must adhere to
         * @param needLicense       :true = allows for displaying with proper attribution.
         */
        @GET("recipes/random")
        Call<List<RecipeModel>> findRandomRecipes(
                @Query("number") int numberOfRecipes,
                @Query("tags") String tags,
                @Query("limitLicense") boolean needLicense
        );


        /** @Params

        query   : What to search for
        type    : Type of course one of the following:
                  main course, side dish, dessert, appetizer, salad, bread,
                  breakfast, soup, beverage, sauce, or drink

        diet    : Only include from these diets: comma-separated
        cuisine : Only include from these diets: comma-separated

        instructionsRequired : Are instructions needed?
        intolerances :  Exclude all these items: comma-separated

        number : number of recipes in result 0-100
        offset : what page to start on 0-900

        limitLicense : Is author name/info needed? **/
        @GET("recipes/search")
        Call<List<RecipeModel>> searchAllRecipes(
                @Query("query") String query,       // What to search for
                @Query("type") String course,       // Type of course one of the following
                                                    //main course, side dish, dessert, appetizer, salad, bread, breakfast, soup, beverage, sauce, or drink
                @Query("diet") String diet,         // Only include from these diets: comma-separated
                @Query("cuisine") String tags,      // Only include from these cuisine: comma-separated
                @Query("instructionsRequired") boolean instructionsRequired,
                @Query("intolerances") String intolerances,
                @Query("number") int numberOfRecipes,       // 0-100
                @Query("offset") int offset,        // 0-900
                @Query("limitLicense") boolean needLicense
        );


    }
}