package com.sem4ikt.uni.recipefinderchatbot.rest;

import com.sem4ikt.uni.recipefinderchatbot.model.spoonacular.AnalyzedQueryModel;
import com.sem4ikt.uni.recipefinderchatbot.model.spoonacular.AnswerModel;
import com.sem4ikt.uni.recipefinderchatbot.model.spoonacular.IngredientSubstituteModel;
import com.sem4ikt.uni.recipefinderchatbot.model.spoonacular.IngredientsModel;
import com.sem4ikt.uni.recipefinderchatbot.model.spoonacular.InstructionsModel;
import com.sem4ikt.uni.recipefinderchatbot.model.spoonacular.MealPlanDayModel;
import com.sem4ikt.uni.recipefinderchatbot.model.spoonacular.MealPlanWeekModel;
import com.sem4ikt.uni.recipefinderchatbot.model.spoonacular.NutrientsModel;
import com.sem4ikt.uni.recipefinderchatbot.model.spoonacular.RandomRecipeModel;
import com.sem4ikt.uni.recipefinderchatbot.model.spoonacular.RecipeModel;
import com.sem4ikt.uni.recipefinderchatbot.model.spoonacular.RecipesModel;
import com.sem4ikt.uni.recipefinderchatbot.model.spoonacular.SearchRecipesModel;
import com.sem4ikt.uni.recipefinderchatbot.model.spoonacular.SummaryModel;
import com.sem4ikt.uni.recipefinderchatbot.model.spoonacular.TextModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by mathiaslykkepedersen on 06/03/2017.
 */

public interface ISpoonacularAPI {


    interface ICompute{


        /**@Params

            diet            : What diet it should be, etc vegetarian
        targetCalories  : What is the caloric target for one day? The meal plan generator will try to get as close as possible to that goal.
        timeFrame       : Either for one 'day' or an entire 'week'.
        exclude         : A comma-separated list of allergens or ingredients that must be excluded.

         **/
        @GET("recipes/mealplans/generate?timeFrame=day")
        Call<MealPlanDayModel> getDayMealPlan(
                @Query("diet") String diet,
                @Query("targetCalories") int targetCalories,
                //@Query("timeFrame") String timeFrame,
                @Query("exclude")  String exclude
            );

        @GET("recipes/mealplans/generate?timeFrame=week")
        Call<MealPlanWeekModel> getWeekMealPlan(
                @Query("diet") String diet,
                @Query("targetCalories") int targetCalories,
                @Query("exclude") String exclude
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

        /**
         * @param q : Parse a recipe search query to find out its intention.
         */
        @GET("recipes/queries/analyze")
        Call<AnalyzedQueryModel> getAnalyzedQuery(
                @Query("q") String q
        );

    }

    interface IData{


        /**
         *
         * @param id                :the id of recipe
         * @param includeNutrition  :include nutrition data to the
         *                           recipe information
         */
        @GET("recipes/{id}/information")
        Call<RecipeModel> getRecipe(@Path("id") int id, @Query("includeNutrition") boolean includeNutrition);

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
        Call<List<InstructionsModel>> getRecipeInstructions(
                @Path("id")  int id,
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
        Call<List<NutrientsModel>> findByNutrients(
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
        Call<List<IngredientsModel>> findByIngredients(
                @Query("ingredients") String ingredients,
                @Query("number") int maxNumberOfResults,
                @Query("fillIngredients") Boolean fillIngredients,
                @Query("ranking") int rank
        );

        /**
         *
         * @param id    :id of recipe
         */
        @GET("recipes/{id}/similar")
        Call<List<RecipesModel>> findSimilarRecipes(@Path("id") int id);

        /**
         *
         * @param ingredientName        :ingredientName
         */
        @GET("food/ingredients/substitutes")
        Call<IngredientSubstituteModel> findIngredientSubstitutes(@Query("ingredientName") String ingredientName);

        /**
         *
         * @param numberOfRecipes   :Number of recipes to be returned betwheen [1,100]
         * @param tags              :Tags that random recipes must adhere to
         * @param needLicense       :true = allows for displaying with proper attribution.
         */
        @GET("recipes/random")
        Call<RandomRecipeModel> findRandomRecipes(
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
        Call<SearchRecipesModel> searchAllRecipes(
                @Query("query") String query,
                @Query("type") String course,
                @Query("diet") String diet,
                @Query("cuisine") String tags,
                @Query("excludeIngredients") String exclude,
                @Query("instructionsRequired") boolean instructionsRequired,
                @Query("intolerances") String intolerances,
                @Query("number") int numberOfRecipes,
                @Query("offset") int offset,
                @Query("limitLicense") boolean needLicense
        );


    }
}