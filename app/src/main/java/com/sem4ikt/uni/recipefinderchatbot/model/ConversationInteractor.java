package com.sem4ikt.uni.recipefinderchatbot.model;

import com.sem4ikt.uni.recipefinderchatbot.model.interfaces.IConversationInteractor;
import com.sem4ikt.uni.recipefinderchatbot.model.spoonacular.AnswerModel;
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
