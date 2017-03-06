package com.sem4ikt.uni.recipefinderchatbot.rest;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by mathiaslykkepedersen on 06/03/2017.
 */

public class ApiClient implements IApiClient {

    private static Retrofit retrofit = null;

    private static final String BASE_URL = "https://spoonacular-recipe-food-nutrition-v1.p.mashape.com/";

    public Retrofit getClient(){
        if(retrofit == null)
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

        return retrofit;
    }

}
