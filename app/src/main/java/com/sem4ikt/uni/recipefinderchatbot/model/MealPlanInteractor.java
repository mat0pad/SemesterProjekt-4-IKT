package com.sem4ikt.uni.recipefinderchatbot.model;

import com.sem4ikt.uni.recipefinderchatbot.model.interfaces.IMealPlanInteractor;
import com.sem4ikt.uni.recipefinderchatbot.model.spoonacular.MealPlanDayModel;
import com.sem4ikt.uni.recipefinderchatbot.rest.ApiClient;
import com.sem4ikt.uni.recipefinderchatbot.rest.IApiClient;
import com.sem4ikt.uni.recipefinderchatbot.rest.ISpoonacularAPI;
import com.sem4ikt.uni.recipefinderchatbot.model.spoonacular.MealPlanWeekModel;
import java.util.List;

import retrofit2.Callback;
import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by kaspe on 12-04-2017.
 */

public class MealPlanInteractor implements IMealPlanInteractor {

    ApiClient client;
    MealPlanDayModel dayModel;
    MealPlanWeekModel weekModel;

    MealPlanInteractor() {client = new ApiClient();}


   public void getmealDayPlan(String diet, int calories, String without) {
        ISpoonacularAPI.ICompute apiService = client.getClient().create(ISpoonacularAPI.ICompute.class);

        apiService.getDayMealPlan(diet, calories, without).enqueue(new Callback<MealPlanDayModel>() {
            @Override
            public void onResponse(Call<MealPlanDayModel> call, Response<MealPlanDayModel> response) {

                if (response.code() == 200) {
                    dayModel = response.body();
                }
            }

            @Override
            public void onFailure(Call<MealPlanDayModel> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    public void getmealWeekPlan(String diet, int calories, String without) {
        ISpoonacularAPI.ICompute apiService = client.getClient().create(ISpoonacularAPI.ICompute.class);

        apiService.getWeekMealPlan(diet, calories, without).enqueue(new Callback<MealPlanWeekModel>() {
            @Override
            public void onResponse(Call<MealPlanWeekModel> call, Response<MealPlanWeekModel> response) {

                if (response.code() == 200) {
                    weekModel = response.body();
                }
            }

            @Override
            public void onFailure(Call<MealPlanWeekModel> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

}

