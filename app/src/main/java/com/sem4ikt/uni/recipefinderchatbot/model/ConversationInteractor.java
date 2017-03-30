package com.sem4ikt.uni.recipefinderchatbot.model;

import com.sem4ikt.uni.recipefinderchatbot.model.interfaces.IConversationInteractor;
import com.sem4ikt.uni.recipefinderchatbot.model.spoonacular.AnalyzedQueryModel;
import com.sem4ikt.uni.recipefinderchatbot.model.spoonacular.AnswerModel;
import com.sem4ikt.uni.recipefinderchatbot.model.spoonacular.RandomRecipeModel;
import com.sem4ikt.uni.recipefinderchatbot.model.spoonacular.TextModel;
import com.sem4ikt.uni.recipefinderchatbot.presenter.interfaces.IChatbotPresenter;
import com.sem4ikt.uni.recipefinderchatbot.rest.ApiClient;
import com.sem4ikt.uni.recipefinderchatbot.rest.IApiClient;
import com.sem4ikt.uni.recipefinderchatbot.rest.ISpoonacularAPI;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by mathiaslykkepedersen on 20/03/2017.
 */

public class ConversationInteractor implements IConversationInteractor {

    private IApiClient client;
    private IChatbotPresenter presenter;

    public ConversationInteractor(IChatbotPresenter presenter){
        client = new ApiClient();
        this.presenter = presenter;
    }


    @Override
    public void performAction(String action, String input) {

        switch (action){

            case "joke":
                invokeJoke();
                break;

            case "trivia":
                invokeTrivia();
                break;

            case "fact":
                invokeFact(input);
                break;

            case "single_random_recipe":
                detectQueryIntention(input);
                break;

            default:
                presenter.showText(action);
                break;
        }
    }

    @Override
    public void switchWorkspace(String which, String lastInput) {

        if(which.equals("recipe")){
            presenter.switchWorkspace(1, lastInput);
        }
        else {
            presenter.switchWorkspace(0, lastInput);
        }
    }

    private void invokeJoke(){

        ISpoonacularAPI.IData apiService = client.getClient().create(ISpoonacularAPI.IData.class);

        Call<TextModel> call = apiService.getRandomFoodJoke();

        enqueueTextModelCall(call);
    }

    private void invokeTrivia(){

        ISpoonacularAPI.IData apiService = client.getClient().create(ISpoonacularAPI.IData.class);

        Call<TextModel> call = apiService.getFoodTrivia();

        enqueueTextModelCall(call);

    }

    private void invokeFact(String input){

        ISpoonacularAPI.ICompute apiService = client.getClient().create(ISpoonacularAPI.ICompute.class);

        Call<AnswerModel> call = apiService.getQuickAnswer(input);

        call.enqueue(new Callback<AnswerModel>() {
            @Override
            public void onResponse(Call<AnswerModel> call, Response<AnswerModel> response) {

                if(response.code() == 200){

                    AnswerModel model = response.body();
                    presenter.showText(model.getAnswer());
                }
                else
                    presenter.showText(null);
            }

            @Override
            public void onFailure(Call<AnswerModel> call, Throwable t) {
                presenter.showText(null);
            }
        });
    }

    private void detectQueryIntention(String input) {

        ISpoonacularAPI.ICompute apiService = client.getClient().create(ISpoonacularAPI.ICompute.class);

        Call<AnalyzedQueryModel> call = apiService.getAnalyzedQuery(input);

        call.enqueue(new Callback<AnalyzedQueryModel>() {
            @Override
            public void onResponse(Call<AnalyzedQueryModel> call, Response<AnalyzedQueryModel> response) {

                if (response.code() == 200) {

                    AnalyzedQueryModel model = response.body();

                    String query = "";

                    for (int i = 0; i < model.getIngredients().size(); i++)
                        if (model.getIngredients().get(i).getInclude())
                            query += model.getIngredients().get(i).getName() + ",";

                    for (int i = 0; i < model.getDishes().size(); i++)
                        query += model.getDishes().get(i).getName() + ",";

                    for (int i = 0; i < model.getModifiers().size(); i++)
                        query += model.getModifiers().get(i).getName() + ",";

                    for (int i = 0; i < model.getCuisines().size(); i++)
                        query += model.getCuisines().get(i).getName() + ",";

                    if (query.length() != 0 && query.charAt(query.length() - 1) == ',')
                        query = query.substring(0, query.length() - 1);

                    System.out.println(query);

                    randomRecipe(query);
                } else
                    System.out.println("Failed detectQueryIntention");
            }

            @Override
            public void onFailure(Call<AnalyzedQueryModel> call, Throwable t) {
                System.out.println("Failed detectQueryIntention");
            }
        });

    }

    private void randomRecipe(String intent) {

        ISpoonacularAPI.ISearch apiService = client.getClient().create(ISpoonacularAPI.ISearch.class);

        Call<RandomRecipeModel> call = apiService.findRandomRecipes(1, intent, false);

        call.enqueue(new Callback<RandomRecipeModel>() {
            @Override
            public void onResponse(Call<RandomRecipeModel> call, Response<RandomRecipeModel> response) {

                if (response.code() == 200) {
                    RandomRecipeModel model = response.body();
                    presenter.showSingleRecipeText("Okay I found this:", model.getRecipesList().get(0).getImage(), model.getRecipesList().get(0).getId());
                    //presenter.showText("Recipe name is " + model.getRecipesList().get(0).getTitle());
                } else {
                    System.out.println("Failed randomRecipe - code is " + response.code());
                    presenter.showText("I'm sorry I couldn't find any...");
                }
            }

            @Override
            public void onFailure(Call<RandomRecipeModel> call, Throwable t) {
                System.out.println("Failed randomRecipe");
                presenter.showText(null);
            }
        });
    }

    private void enqueueTextModelCall(Call<TextModel> call){

        call.enqueue(new Callback<TextModel>() {
            @Override
            public void onResponse(Call<TextModel> call, Response<TextModel> response) {

                if(response.code() == 200){

                    TextModel model = response.body();
                    presenter.showText(model.getText());
                }
                else
                    presenter.showText(null);
            }

            @Override
            public void onFailure(Call<TextModel> call, Throwable t) {
                presenter.showText(null);
            }
        });
    }

}
