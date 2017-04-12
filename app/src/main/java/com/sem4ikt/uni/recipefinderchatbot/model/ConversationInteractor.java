package com.sem4ikt.uni.recipefinderchatbot.model;

import com.ibm.watson.developer_cloud.conversation.v1.model.MessageResponse;
import com.sem4ikt.uni.recipefinderchatbot.model.interfaces.IConversationInteractor;
import com.sem4ikt.uni.recipefinderchatbot.model.spoonacular.AnalyzedQueryModel;
import com.sem4ikt.uni.recipefinderchatbot.model.spoonacular.AnswerModel;
import com.sem4ikt.uni.recipefinderchatbot.model.spoonacular.IngredientSubstituteModel;
import com.sem4ikt.uni.recipefinderchatbot.model.spoonacular.IngredientsModel;
import com.sem4ikt.uni.recipefinderchatbot.model.spoonacular.NutrientsModel;
import com.sem4ikt.uni.recipefinderchatbot.model.spoonacular.RandomRecipeModel;
import com.sem4ikt.uni.recipefinderchatbot.model.spoonacular.RecipesModel;
import com.sem4ikt.uni.recipefinderchatbot.model.spoonacular.SearchRecipesModel;
import com.sem4ikt.uni.recipefinderchatbot.model.spoonacular.TextModel;
import com.sem4ikt.uni.recipefinderchatbot.presenter.interfaces.IChatbotPresenter;
import com.sem4ikt.uni.recipefinderchatbot.rest.ApiClient;
import com.sem4ikt.uni.recipefinderchatbot.rest.IApiClient;
import com.sem4ikt.uni.recipefinderchatbot.rest.ISpoonacularAPI;

import java.util.Arrays;
import java.util.List;

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
    public void performAction(String action, MessageResponse response) {

        switch (action){

            case "joke":
                invokeJoke();
                break;

            case "trivia":
                invokeTrivia();
                break;

            case "fact":
                invokeFact(response.getInputText());
                break;

            case "random_recipe":
                detectQueryIntention(response.getContext().get("with_query").toString(), response.getContext().get("num_of_recipes").toString());
                break;

            case "recipe_dicc":
                findRecipesBasedOnDICC(response.getContext().get("num_of_recipes").toString()
                        , response.getContext().get("diet").toString()
                        , response.getContext().get("intolerance").toString()
                        , response.getContext().get("cuisine").toString()
                        , response.getContext().get("course").toString()
                        , response.getContext().get("with_query").toString()
                        , response.getContext().get("exclude").toString());
                break;

            case "ingredient_substitute":
                detectIngredientsForSubstitutes(response.getInputText());

            default:
                presenter.showText("I don't known this action: " + action);
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

    // Used to get random food joke
    private void invokeJoke(){

        ISpoonacularAPI.IData apiService = client.getClient().create(ISpoonacularAPI.IData.class);

        Call<TextModel> call = apiService.getRandomFoodJoke();

        enqueueTextModelCall(call);
    }

    // Used to get random trivia
    private void invokeTrivia(){

        ISpoonacularAPI.IData apiService = client.getClient().create(ISpoonacularAPI.IData.class);

        Call<TextModel> call = apiService.getFoodTrivia();

        enqueueTextModelCall(call);

    }

    // Used to get random fact
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
                    presenter.showErrorText();
            }

            @Override
            public void onFailure(Call<AnswerModel> call, Throwable t) {
                presenter.showErrorText();
            }
        });
    }

    // Used to detect food in query before random search
    private void detectQueryIntention(String input, final String numOfRecipes) {

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

                    randomRecipe(query, stringToInt(numOfRecipes));
                } else {
                    System.out.println("Failed detectQueryIntention");
                    presenter.showErrorText();
                }
            }

            @Override
            public void onFailure(Call<AnalyzedQueryModel> call, Throwable t) {
                System.out.println("Failed detectQueryIntention");
                t.printStackTrace();
                presenter.showErrorText();
            }
        });

    }

    // The actual random recipe search
    private void randomRecipe(String intent, int numOfRecipes) {

        ISpoonacularAPI.ISearch apiService = client.getClient().create(ISpoonacularAPI.ISearch.class);

        Call<RandomRecipeModel> call = apiService.findRandomRecipes((numOfRecipes < 1 ? 1 : numOfRecipes), intent, false);

        call.enqueue(new Callback<RandomRecipeModel>() {
            @Override
            public void onResponse(Call<RandomRecipeModel> call, Response<RandomRecipeModel> response) {

                if (response.code() == 200) {
                    RandomRecipeModel model = response.body();

                    if (model.getRecipesList().size() > 1) {
                        presenter.showMoreRecipesText("I found these:", model.getRecipesList().get(1).getImage(), model.getRecipesList(), MessageModel.TYPE.MORE_RECIPE_MODEL);
                    } else if (model.getRecipesList().size() == 1)
                        presenter.showSingleRecipeText("I found this:", model.getRecipesList().get(0).getImage(), model.getRecipesList().get(0).getId());

                    else
                        presenter.showText("I'm sorry I couldn't find any...");

                } else {
                    System.out.println("Failed randomRecipe - code is " + response.code());
                    presenter.showText("I'm sorry I couldn't find any...");
                }
            }

            @Override
            public void onFailure(Call<RandomRecipeModel> call, Throwable t) {
                System.out.println("Failed randomRecipe");

                t.printStackTrace();

                presenter.showErrorText();
            }
        });
    }

    // All normal text asynx calls queued here
    private void enqueueTextModelCall(Call<TextModel> call){

        call.enqueue(new Callback<TextModel>() {
            @Override
            public void onResponse(Call<TextModel> call, Response<TextModel> response) {

                if(response.code() == 200){

                    TextModel model = response.body();
                    presenter.showText(model.getText());
                }
                else
                    presenter.showErrorText();
            }

            @Override
            public void onFailure(Call<TextModel> call, Throwable t) {
                presenter.showErrorText();
            }
        });
    }

    // The actual finding of recipes based on DICC: Diet, Intolerance, Cuisine, Course
    private void findRecipesBasedOnDICC(String numOfRecipes, String diet, String intolerance, String cuisine, String course, String query, String exclude) {

        int num = stringToInt(numOfRecipes);

        ISpoonacularAPI.ISearch apiService = client.getClient().create(ISpoonacularAPI.ISearch.class);

        System.out.println("Query: " + query + "\nDiet: " + diet + "\nCuisine: " + cuisine + "\nCourse: " + course + "\nIntolerance: " + intolerance);

        Call<SearchRecipesModel> call = apiService
                .searchAllRecipes((query.equals("undefined") ? "" : query)
                        , (course.equals("undefined") ? "" : course)
                        , (diet.equals("undefined") ? "" : diet)
                        , (cuisine.equals("undefined") ? "" : cuisine)
                        , (exclude.equals("undefined") ? "" : exclude)
                        , false
                        , (intolerance.equals("undefined") ? "" : intolerance)
                        , num, 0, false);

        call.enqueue(new Callback<SearchRecipesModel>() {
            @Override
            public void onResponse(Call<SearchRecipesModel> call, Response<SearchRecipesModel> response) {

                if (response.code() == 200) {
                    SearchRecipesModel model = response.body();

                    if (model.getResults().size() > 1) {

                        String image = "";

                        for (RecipesModel item : model.getResults())
                            if (!item.getImage().isEmpty())
                                image = item.getImage();

                        presenter.showMoreRecipesText("I found these:", image, model.getResults(), MessageModel.TYPE.MORE_RECIPES_MODEL);
                    } else if (model.getResults().size() == 1)
                        presenter.showSingleRecipeText("I found this:", model.getResults().get(0).getImage(), model.getResults().get(0).getId());

                    else
                        presenter.showText("I'm sorry I couldn't find any...");

                } else {
                    System.out.println("Failed findRecipesBasedOnDICC - code is " + response.code());
                    presenter.showText("I'm sorry I couldn't find any...");
                }
            }

            @Override
            public void onFailure(Call<SearchRecipesModel> call, Throwable t) {
                System.out.println("Failed findRecipesBasedOnDICC");

                t.printStackTrace();

                presenter.showErrorText();
            }
        });
    }

    // The actual finding of recipes based on ingredients
    private void findByIngredients(String numOfRecipes, String ingredients, int rank) {
        int num = stringToInt(numOfRecipes);

        ISpoonacularAPI.ISearch apiService = client.getClient().create(ISpoonacularAPI.ISearch.class);

        Call<List<IngredientsModel>> call = apiService.findByIngredients(ingredients, num, true, rank);

        call.enqueue(new Callback<List<IngredientsModel>>() {
            @Override
            public void onResponse(Call<List<IngredientsModel>> call, Response<List<IngredientsModel>> response) {

                if (response.code() == 200) {
                    List<IngredientsModel> model = response.body();

                    for (IngredientsModel item : model)
                        System.out.println(item.getTitle());

                    if (model.size() > 1) {
                        presenter.showMoreRecipesText("Okay I found " + model.size() + " in total:",
                                model.get(1).getImage(), model, MessageModel.TYPE.MORE_INGREDIENTS_MODEL);
                    } else if (model.size() == 1) {

                        IngredientsModel item = model.get(0);

                        presenter.showSingleRecipeText("How about \"" + item.getTitle() + "\"? It has " + item.getUsedIngredientCount() + " of the ingredients you mentioned"
                                , item.getImage(), item.getId());
                    } else
                        presenter.showText("I'm sorry I couldn't find any...");

                } else {
                    System.out.println("Failed findByIngredients - code is " + response.code());
                    presenter.showText("I'm sorry I couldn't find any...");
                }
            }

            @Override
            public void onFailure(Call<List<IngredientsModel>> call, Throwable t) {
                System.out.println("Failed findByIngredients");

                t.printStackTrace();

                presenter.showErrorText();
            }
        });
    }

    // The actual finding of recipes based on nutrients
    private void findByNutrients(String numOfRecipes) {

        int num = stringToInt(numOfRecipes);

        ISpoonacularAPI.ISearch apiService = client.getClient().create(ISpoonacularAPI.ISearch.class);

        Call<List<NutrientsModel>> call = apiService.findByNutrients(0, 0, 0, 0, 0, 0, 0, 0, num, 0, true);

        call.enqueue(new Callback<List<NutrientsModel>>() {
            @Override
            public void onResponse(Call<List<NutrientsModel>> call, Response<List<NutrientsModel>> response) {

                if (response.code() == 200) {
                    List<NutrientsModel> model = response.body();

                    for (NutrientsModel item : model)
                        System.out.println(item.getTitle());

                    if (model.size() > 1) {
                        presenter.showMoreRecipesText("Okay I found " + model.size() + " in total:",
                                model.get(1).getImage(), model, MessageModel.TYPE.MORE_NUTRIENTS_MODEL);
                    } else if (model.size() == 1) {

                        NutrientsModel item = model.get(0);

                        presenter.showSingleRecipeText("How about \"" + item.getTitle() + "\"? It has " + item.getCalories() + " Calories"
                                , item.getImage(), item.getId());
                    } else
                        presenter.showText("I'm sorry I couldn't find any...");

                } else {
                    System.out.println("Failed findByIngredients - code is " + response.code());
                    presenter.showText("I'm sorry I couldn't find any...");
                }
            }

            @Override
            public void onFailure(Call<List<NutrientsModel>> call, Throwable t) {
                System.out.println("Failed findByIngredients");

                t.printStackTrace();

                presenter.showErrorText();
            }
        });
    }

    // Used to detect ingredients in query before substitutes search
    private void detectIngredientsForSubstitutes(String input) {

        ISpoonacularAPI.ICompute apiService = client.getClient().create(ISpoonacularAPI.ICompute.class);

        Call<AnalyzedQueryModel> call = apiService.getAnalyzedQuery(input);

        call.enqueue(new Callback<AnalyzedQueryModel>() {
            @Override
            public void onResponse(Call<AnalyzedQueryModel> call, Response<AnalyzedQueryModel> response) {

                if (response.code() == 200) {

                    AnalyzedQueryModel model = response.body();

                    String[] query = new String[model.getIngredients().size() - 1];

                    for (int i = 0; i < model.getIngredients().size(); i++)
                        if (model.getIngredients().get(i).getInclude())
                            query[i] = model.getIngredients().get(i).getName();

                    System.out.println(Arrays.toString(query));

                    for (String aQuery : query)
                        if (!aQuery.isEmpty())
                            findIngredientSubstitutes(aQuery);

                } else {
                    System.out.println("Failed detectIngredientsForSubstitutes");
                    presenter.showErrorText();
                }
            }

            @Override
            public void onFailure(Call<AnalyzedQueryModel> call, Throwable t) {
                System.out.println("Failed detectIngredientsForSubstitutes");
                t.printStackTrace();
                presenter.showErrorText();
            }
        });

    }

    // The actual finding of ingredient substitutes based on ingredient name
    private void findIngredientSubstitutes(String ingredientName) {

        ISpoonacularAPI.ISearch apiService = client.getClient().create(ISpoonacularAPI.ISearch.class);

        Call<IngredientSubstituteModel> call = apiService.findIngredientSubstitutes(ingredientName);

        call.enqueue(new Callback<IngredientSubstituteModel>() {
            @Override
            public void onResponse(Call<IngredientSubstituteModel> call, Response<IngredientSubstituteModel> response) {

                if (response.code() == 200) {
                    IngredientSubstituteModel model = response.body();

                    if (model.getStatus().equals("success")) {

                        if (model.getSubstitutes().size() > 1) {
                            presenter.showText(model.getMessage().replace('.', ' ') + model.getIngredient() + ". They are..");

                            for (int i = 0; i < model.getSubstitutes().size(); i++)
                                presenter.showText(i + 1 + ".\n" + model.getSubstitutes().get(i));
                        } else if (model.getSubstitutes().size() == 1) {
                            presenter.showText(model.getMessage().replace('.', ' ') + model.getIngredient() + ". It is..");

                            presenter.showText(model.getSubstitutes().get(0));
                        } else
                            presenter.showText("I'm sorry I couldn't find any substitutes for that ingredient...");
                    } else
                        presenter.showText("I'm sorry I couldn't find any substitutes for that ingredient...");

                } else {
                    System.out.println("Failed findIngredientSubstitutes - code is " + response.code());
                    presenter.showText("I'm sorry I couldn't find any...");
                }
            }

            @Override
            public void onFailure(Call<IngredientSubstituteModel> call, Throwable t) {
                System.out.println("Failed findIngredientSubstitutes");

                t.printStackTrace();

                presenter.showErrorText();
            }
        });

    }

    // Convert to int from string where float, double or int
    private int stringToInt(String num) {

        // Check to see if numOfRecipes is float or int
        if (num.contains(".") || num.contains(","))
            return (int) Float.parseFloat(num);
        else
            return Integer.parseInt(num);


    }
}
