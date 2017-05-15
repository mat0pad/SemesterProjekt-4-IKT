package com.sem4ikt.uni.recipefinderchatbot.IntegrationTest.model;

import android.util.Log;

import com.ibm.watson.developer_cloud.android.library.audio.StreamPlayer;
import com.ibm.watson.developer_cloud.conversation.v1.model.MessageResponse;
import com.sem4ikt.uni.recipefinderchatbot.model.ConversationInteractor;
import com.sem4ikt.uni.recipefinderchatbot.model.MessageModel;
import com.sem4ikt.uni.recipefinderchatbot.model.spoonacular.IngredientModel;
import com.sem4ikt.uni.recipefinderchatbot.model.spoonacular.IngredientsModel;
import com.sem4ikt.uni.recipefinderchatbot.model.spoonacular.InstructionsModel;
import com.sem4ikt.uni.recipefinderchatbot.model.spoonacular.MealPlanDayModel;
import com.sem4ikt.uni.recipefinderchatbot.model.spoonacular.MealPlanWeekModel;
import com.sem4ikt.uni.recipefinderchatbot.model.spoonacular.RandomRecipeModel;
import com.sem4ikt.uni.recipefinderchatbot.model.spoonacular.RecipeModel;
import com.sem4ikt.uni.recipefinderchatbot.presenter.interfaces.IChatbotPresenter;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.theories.PotentialAssignment;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

import static android.webkit.ConsoleMessage.MessageLevel.LOG;


/**
 * Created by anton on 30-04-2017.
 */

public class ConversationInteractorIntegrationTest {

    private ConversationInteractor interactor;

    private CountDownLatch signal;

    private boolean didReceiv;

    private static final String KEY = "text";

    private static final String UNDEFINED = "undefined";

    private static final int numRecipes = 10;

    private String _text;

    private static String _name = "anton";




    @Before
    public void setup()
    {
        _text = null;
        didReceiv = false;

        interactor = new ConversationInteractor();

        interactor.setPresenter(new IChatbotPresenter()
        {
            @Override
            public void send(String input) {

            }

            @Override
            public void switchWorkspace(int spaceId, String lastInput) {
                if(spaceId == 1)
                    didReceiv = (lastInput == UNDEFINED);
                else
                    didReceiv = (lastInput == KEY);
                signal.countDown();
            }

            @Override
            public void showErrorText() {

            }

            @Override
            public void showText(String text) {
                _text = text;
                signal.countDown();

            }

            @Override
            public void showSingleRecipeText(String msg, String img, int id) {
                if(!msg.isEmpty() && !img.isEmpty())
                    didReceiv = true;
                signal.countDown();
            }

            @Override
            public void showMoreRecipesText(String msg, String img, Object obj, MessageModel.TYPE type) {
                switch (type)
                {
                    case MORE_RECIPE_MODEL:
                        if(((List<RecipeModel>) obj).size() == numRecipes)
                            didReceiv = true;
                        break;
                    case MORE_RECIPES_MODEL:
                        if(((List<RecipeModel>) obj).size() == numRecipes)
                            didReceiv = true;
                        break;
                    case MORE_INGREDIENTS_MODEL:
                        if(((List<IngredientsModel>) obj).size() == numRecipes)
                            didReceiv = true;
                        break;
                    case MORE_NUTRIENTS_MODEL:
                        if(((List<IngredientsModel>) obj).size() == numRecipes)
                            didReceiv = true;
                        break;

                }
                signal.countDown();
            }

            @Override
            public void getUser() {

            }

            @Override
            public void doInitText2Speech() {

            }

            @Override
            public void updateUser(String name, String response) {
                if(_name == name)
                    didReceiv = true;
                signal.countDown();
            }

            @Override
            public void addMealPlanWeek(MealPlanWeekModel model, Date date) {

            }

            @Override
            public void addMealPlanDay(MealPlanDayModel model, Date date) {
                didReceiv = true;
                signal.countDown();
            }

            @Override
            public void setView(Object view) {

            }

            @Override
            public void clearView() {

            }
        });
    }



    @Test
    public void performActionJokeTriviaReceivedCallTextNotEmpty() throws InterruptedException {
        signal = new CountDownLatch(1);

        interactor.performAction("joke",new MessageResponse());

        signal.await();

        Assert.assertTrue(!(_text.isEmpty()));

    }

    @Test
    public void performTriviaReceivedCallTextNotEmpty() throws InterruptedException {
        signal = new CountDownLatch(1);
        interactor.performAction("trivia",new MessageResponse());

        signal.await();

        Assert.assertTrue(!(_text.isEmpty()));
    }

    @Test
    public void performFactReceivedCall() throws InterruptedException {
        signal = new CountDownLatch(1);
        MessageResponse messageResponse = new MessageResponse();
        Map<String,Object> input = new HashMap<>();

        input.put(KEY,"How much vitamin c is in 2 apples?");
        messageResponse.setInput(input);


        interactor.performAction("fact",messageResponse);

        signal.await();

        Assert.assertTrue(!(_text.isEmpty()));
    }

    @Test
    public void performRandomRecipeSingleReceivedCall() throws InterruptedException {
        signal = new CountDownLatch(1);
        MessageResponse messageResponse = new MessageResponse();
        Map<String,Object> context = new HashMap<>();

        context.put("with_query","dessert");
        context.put("num_of_recipes",1);
        messageResponse.setContext(context);

        interactor.performAction("random_recipe",messageResponse);

        signal.await();

        Assert.assertTrue(didReceiv);

    }

    @Test
    public void performRandomRecipeMoreReceivedCall() throws InterruptedException {
        signal = new CountDownLatch(1);
        MessageResponse messageResponse = new MessageResponse();
        Map<String,Object> context = new HashMap<>();

        context.put("with_query","dessert");
        context.put("num_of_recipes",numRecipes);
        messageResponse.setContext(context);

        interactor.performAction("random_recipe",messageResponse);

        signal.await();

        Assert.assertTrue(didReceiv);

    }

    @Test
    public void performRecipeDiccSingle() throws InterruptedException {
        signal = new CountDownLatch(1);
        MessageResponse messageResponse = new MessageResponse();
        Map<String,Object> context = new HashMap<>();

        context.put("num_of_recipes",1);
        context.put("diet",UNDEFINED);
        context.put("intolerance",UNDEFINED);
        context.put("cuisine",UNDEFINED);
        context.put("course",UNDEFINED);
        context.put("with_query",UNDEFINED);
        context.put("exclude",UNDEFINED);
        messageResponse.setContext(context);

        interactor.performAction("recipe_dicc",messageResponse);

        signal.await();

        Assert.assertTrue(didReceiv);

    }

    @Test
    public void performRecipeDiccMore() throws InterruptedException {
        signal = new CountDownLatch(1);
        MessageResponse messageResponse = new MessageResponse();
        Map<String,Object> context = new HashMap<>();

        context.put("num_of_recipes",numRecipes);
        context.put("diet",UNDEFINED);
        context.put("intolerance",UNDEFINED);
        context.put("cuisine",UNDEFINED);
        context.put("course",UNDEFINED);
        context.put("with_query",UNDEFINED);
        context.put("exclude",UNDEFINED);
        messageResponse.setContext(context);

        interactor.performAction("recipe_dicc",messageResponse);

        signal.await();

        Assert.assertTrue(didReceiv);

    }

    @Test
    public void performSubstituteReceive() throws InterruptedException {
        signal = new CountDownLatch(2);

        MessageResponse messageResponse = new MessageResponse();

        Map<String,Object> input = new HashMap<>();

        input.put(KEY,"apple");

        messageResponse.setInput(input);

        interactor.performAction("substitute",messageResponse);

        signal.await();

        Assert.assertTrue(true);
    }

    @Test
    public void performFindByIngredientReceiveMore() throws InterruptedException {
        signal = new CountDownLatch(1);

        MessageResponse messageResponse = new MessageResponse();
        Map<String,Object> context = new HashMap<>();

        context.put("with_query","apple, banana");
        context.put("num_of_recipes", numRecipes);
        context.put("rank",1);

        messageResponse.setContext(context);

        interactor.performAction("find_by_ingredient",messageResponse);

        signal.await();

        Assert.assertTrue(didReceiv);

    }

    @Test
    public void performFindByNutrientReceive() throws InterruptedException {
        signal = new CountDownLatch(1);

        MessageResponse messageResponse = new MessageResponse();
        Map<String,Object> context = new HashMap<>();

        context.put("num_of_recipes", numRecipes);
        context.put("nutrient_type",UNDEFINED);

        messageResponse.setContext(context);

        interactor.performAction("find_by_nutrient",messageResponse);

        signal.await();

        Assert.assertTrue(didReceiv);
    }

    @Test
    public void performFindMealplanDayReceive() throws InterruptedException {
        signal = new CountDownLatch(1);

        MessageResponse messageResponse = new MessageResponse();
        Map<String,Object> context = new HashMap<>();

        context.put("diet",UNDEFINED);
        context.put("calories",2000);
        context.put("duration","week");
        context.put("start_date",UNDEFINED);

        messageResponse.setContext(context);

        interactor.performAction("mealplan",messageResponse);

        signal.await();

        Assert.assertTrue(didReceiv);
    }

    @Test
    public void performFindSaveUserReceive() throws InterruptedException {
        signal = new CountDownLatch(1);

        MessageResponse messageResponse = new MessageResponse();
        Map<String,Object> context = new HashMap<>();
        Map<String,Object> text = new HashMap<>();


        context.put("username",_name);

        messageResponse.setContext(context);

        List<String> list = new ArrayList<>();

        list.add(UNDEFINED);

        text.put(KEY,list);

        messageResponse.setOutput(text);

        messageResponse.getText().get(0).toString();

        interactor.performAction("save_user",messageResponse);

        signal.await();

        Assert.assertTrue(didReceiv);
    }

    @Test
    public void switchWorkspaceRecipe() throws InterruptedException {
        signal = new CountDownLatch(1);

        interactor.switchWorkspace("recipe",UNDEFINED);

        signal.await();

        Assert.assertTrue(didReceiv);
    }

    @Test
    public void switchWorkspaceTemp() throws InterruptedException {
        signal = new CountDownLatch(1);

        interactor.switchWorkspace("temp",KEY);

        signal.await();

        Assert.assertTrue(didReceiv);
    }

}
