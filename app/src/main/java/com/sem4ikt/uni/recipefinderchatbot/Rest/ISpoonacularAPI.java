package com.sem4ikt.uni.recipefinderchatbot.Rest;

import com.sem4ikt.uni.recipefinderchatbot.Model.RecipeModel;
import com.sem4ikt.uni.recipefinderchatbot.Model.Spoonacular.NutrientsModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by mathiaslykkepedersen on 06/03/2017.
 */

public interface ISpoonacularAPI {


    @Headers({ "X-Mashape-Key: sZ5SigKs5xmshAE2m0byUGB3Q8AZp1VCiP8jsnU7s14kbiwWRP", "Accept: application/json" })
    @GET("recipes/{id}/information")
    Call<RecipeModel> getRecipe(@Path("id") int id, @Query("includeNutrition") Boolean includeNutrition);


    @Headers({ "X-Mashape-Key: sZ5SigKs5xmshAE2m0byUGB3Q8AZp1VCiP8jsnU7s14kbiwWRP", "Accept: application/json" })
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


    @Headers({ "X-Mashape-Key: sZ5SigKs5xmshAE2m0byUGB3Q8AZp1VCiP8jsnU7s14kbiwWRP", "Accept: application/json" })
    @GET("recipes/findByIngredients")
    Call<NutrientsModel> findByIngredients(
            @Query("ingredients") String ingredients,
            @Query("number") int maxNumberOfResults,               // default 5
            @Query("fillIngredients") Boolean fillIngredients,
            @Query("ranking") int rank                            // Whether to maximize used ingredients (1) or minimize missing ingredients (2) first.
    );


}