package com.sem4ikt.uni.recipefinderchatbot.model;

import com.sem4ikt.uni.recipefinderchatbot.model.interfaces.IMealPlanInteractor;
import com.sem4ikt.uni.recipefinderchatbot.rest.ApiClient;
import com.sem4ikt.uni.recipefinderchatbot.rest.IApiClient;
import com.sem4ikt.uni.recipefinderchatbot.rest.ISpoonacularAPI;
import com.sem4ikt.uni.recipefinderchatbot.model.spoonacular.MealPlanModel;
import java.util.List;

import retrofit2.Callback;
import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by kaspe on 12-04-2017.
 */

public class MealPlanInteractor implements IMealPlanInteractor {

    ApiClient client;
    MealPlanModel model;

    MealPlanInteractor() {client = new ApiClient();}


   public MealPlanModel getmealPlan(String diet,int calories,String time,String without) {
        ISpoonacularAPI.ICompute apiService = client.getClient().create(ISpoonacularAPI.ICompute.class);

        apiService.getMealPlan(diet, calories, time, without).enqueue(new Callback<MealPlanModel>() {
            @Override
            public void onResponse(Call<MealPlanModel> call, Response<MealPlanModel> response) {

                if (response.code() == 200) {
                    model = response.body();
                    System.out.println("works: " + model.getNutrients().getCalories());
                }
            }

            @Override
            public void onFailure(Call<MealPlanModel> call, Throwable t) {
                t.printStackTrace();
            }
        });
        return model;
    }
}

