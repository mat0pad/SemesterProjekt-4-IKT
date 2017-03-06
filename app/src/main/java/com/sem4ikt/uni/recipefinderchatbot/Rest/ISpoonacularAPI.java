package com.sem4ikt.uni.recipefinderchatbot.Rest;

import com.sem4ikt.uni.recipefinderchatbot.Model.RecipeModel;

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

}