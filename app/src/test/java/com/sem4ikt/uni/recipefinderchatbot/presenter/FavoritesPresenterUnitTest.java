package com.sem4ikt.uni.recipefinderchatbot.presenter;

import com.sem4ikt.uni.recipefinderchatbot.database.Interface.ICallbackRecipe;
import com.sem4ikt.uni.recipefinderchatbot.database.Interface.IFirebaseDBInteractors;
import com.sem4ikt.uni.recipefinderchatbot.model.spoonacular.RecipeModel;
import com.sem4ikt.uni.recipefinderchatbot.view.IFavoritesView;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * Created by mathiaslykkepedersen on 30/03/2017.
 */

@RunWith(MockitoJUnitRunner.class)
public class FavoritesPresenterUnitTest {

    @Mock
    IFavoritesView view;

    @Mock
    IFirebaseDBInteractors.IRecipeInteractor interactor;

    private FavoritesPresenter presenter;


    @Before
    public void setup() throws Exception {

        presenter = new FavoritesPresenter(view,interactor);
    }

    @Test
    public void clearViewOnDestroy() {
        presenter.clearView();

        Assert.assertEquals(presenter.getView(), null);
    }

    @Test
    public void setView() {
        presenter.clearView();
        presenter.setView(view);

        Assert.assertEquals(presenter.getView(), view);
    }

    @Test
    public void getRecipeListTestBehavior(){
        presenter.getRecipeList();
        verify(interactor, times(1)).getRecipe(presenter);
    }

    @Test
    public void deleteRecipeTestBehavior(){
        presenter.deleteRecipe(null);
        verify(interactor,times(1)).removeRecipe(null);
    }

    @Test
    public void onReceivedDeleteRecipeBehavior(){
        RecipeModel recipe = new RecipeModel();
        presenter.onReceived(recipe, ICallbackRecipe.RECIPE_CALLBACK_TYPE.DELETE_RECIPE);
        verify(view,times(1)).deleteRecipe(recipe);
    }

    @Test
    public void onReceivedAddRecipeBehavior(){
        RecipeModel recipe = new RecipeModel();
        presenter.onReceived(recipe, ICallbackRecipe.RECIPE_CALLBACK_TYPE.ADD_RECIPE);
        verify(view,times(1)).addRecipe(recipe);
    }

    @Test
    public void onReceivedGetRecipeListNoBehavior(){
        presenter.onReceived(null, ICallbackRecipe.RECIPE_CALLBACK_TYPE.GET_RECIPELIST);
        verify(view,times(0)).setList(null);
    }

    @Test
    public void onReceivedGetRecipeListBehavior(){
        presenter.onReceived(new ArrayList<RecipeModel>(), ICallbackRecipe.RECIPE_CALLBACK_TYPE.GET_RECIPELIST);
        verify(view,times(1)).setList(new ArrayList<RecipeModel>());
    }


}