package com.sem4ikt.uni.recipefinderchatbot.model;

import com.sem4ikt.uni.recipefinderchatbot.model.interfaces.IDetailRecipeInteractor;
import com.sem4ikt.uni.recipefinderchatbot.model.spoonacular.InstructionsModel;
import com.sem4ikt.uni.recipefinderchatbot.model.spoonacular.RecipeModel;
import com.sem4ikt.uni.recipefinderchatbot.model.spoonacular.RecipesModel;
import com.sem4ikt.uni.recipefinderchatbot.model.spoonacular.SummaryModel;
import com.sem4ikt.uni.recipefinderchatbot.presenter.DetailRecipePresenter;
import com.sem4ikt.uni.recipefinderchatbot.presenter.interfaces.IDetailRecipeCallback;
import com.sem4ikt.uni.recipefinderchatbot.rest.ApiClient;
import com.sem4ikt.uni.recipefinderchatbot.rest.IApiClient;
import com.sem4ikt.uni.recipefinderchatbot.rest.ISpoonacularAPI;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by mathiaslykkepedersen on 27/03/2017.
 */

public class DetailRecipeInteractor implements IDetailRecipeInteractor {

    private IApiClient client;

    public DetailRecipeInteractor() {
        client = new ApiClient();
    }

    @Override
    public void getRecipe(int id, final IDetailRecipeCallback callback) {

        ISpoonacularAPI.IData apiService = client.getClient().create(ISpoonacularAPI.IData.class);

        Call<RecipeModel> call = apiService.getRecipe(id, false);

        call.enqueue(new Callback<RecipeModel>() {
            @Override
            public void onResponse(Call<RecipeModel> call, Response<RecipeModel> response) {

                if (response.code() == 200) {
                    RecipeModel model = response.body();
                    callback.onReceived(model, DetailRecipePresenter.CALL_TYPE.GET_RECIPE);
                }
            }

            @Override
            public void onFailure(Call<RecipeModel> call, Throwable t) {
                System.out.println("Failed getRecipe reason: " + t.getLocalizedMessage());

                t.printStackTrace();
            }
        });

    }

    @Override
    public void getSummary(int id, final IDetailRecipeCallback callback) {
        ISpoonacularAPI.ICompute apiService3 = client.getClient().create(ISpoonacularAPI.ICompute.class);

        Call<SummaryModel> call3 = apiService3.summarizeRecipe(id);

        call3.enqueue(new Callback<SummaryModel>() {
            @Override
            public void onResponse(Call<SummaryModel> call, Response<SummaryModel> response) {

                if (response.code() == 200) {

                    SummaryModel model = response.body();
                    callback.onReceived(model, DetailRecipePresenter.CALL_TYPE.SUMMARIZE);
                }
            }

            @Override
            public void onFailure(Call<SummaryModel> call, Throwable t) {
                System.out.println("Failed getSummary reason: " + t.getLocalizedMessage());

                t.printStackTrace();
            }
        });
    }

    @Override
    public void getSimilar(int id, final IDetailRecipeCallback callback) {

        ISpoonacularAPI.ISearch apiService = client.getClient().create(ISpoonacularAPI.ISearch.class);

        Call<List<RecipesModel>> call = apiService.findSimilarRecipes(id);

        call.enqueue(new Callback<List<RecipesModel>>() {
            @Override
            public void onResponse(Call<List<RecipesModel>> call, Response<List<RecipesModel>> response) {

                if (response.code() == 200) {

                    List<RecipesModel> model = response.body();
                    callback.onReceived(model, DetailRecipePresenter.CALL_TYPE.SIMILAR);
                }
            }

            @Override
            public void onFailure(Call<List<RecipesModel>> call, Throwable t) {
                System.out.println("Failed getSimilar reason: " + t.getLocalizedMessage());

                t.printStackTrace();
            }
        });

    }

    @Override
    public void getInstructions(int id, final IDetailRecipeCallback callback) {

        ISpoonacularAPI.IData apiService = client.getClient().create(ISpoonacularAPI.IData.class);

        Call<List<InstructionsModel>> call = apiService.getRecipeInstructions(id, true);

        call.enqueue(new Callback<List<InstructionsModel>>() {
            @Override
            public void onResponse(Call<List<InstructionsModel>> call, Response<List<InstructionsModel>> response) {

                if (response.code() == 200) {

                    List<InstructionsModel> model = response.body();

                    callback.onReceived(model, DetailRecipePresenter.CALL_TYPE.INSTRUCTION);
                }
            }

            @Override
            public void onFailure(Call<List<InstructionsModel>> call, Throwable t) {

                System.out.println("Failed getRecipeInstructions reason: " + t.getLocalizedMessage());

                t.printStackTrace();
            }
        });
    }
}
