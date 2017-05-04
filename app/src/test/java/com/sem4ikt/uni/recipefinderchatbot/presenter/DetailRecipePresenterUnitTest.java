package com.sem4ikt.uni.recipefinderchatbot.presenter;

import com.sem4ikt.uni.recipefinderchatbot.activity.DetailRecipeActivity;
import com.sem4ikt.uni.recipefinderchatbot.database.Interface.IFirebaseDBInteractors;
import com.sem4ikt.uni.recipefinderchatbot.model.interfaces.IDetailRecipeInteractor;
import com.sem4ikt.uni.recipefinderchatbot.model.spoonacular.RecipeModel;
import com.sem4ikt.uni.recipefinderchatbot.model.spoonacular.RecipesModel;
import com.sem4ikt.uni.recipefinderchatbot.model.spoonacular.SummaryModel;
import com.sem4ikt.uni.recipefinderchatbot.view.IDetailRecipeView;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * Created by mathiaslykkepedersen on 30/03/2017.
 */

@RunWith(MockitoJUnitRunner.class)
public class DetailRecipePresenterUnitTest {

    @Mock
    IDetailRecipeView view;
    @Mock
    IDetailRecipeInteractor interactor;
    @Mock
    IFirebaseDBInteractors.IRecipeInteractor interactorDB;

    private DetailRecipePresenter presenter;


    @Before
    public void setup() throws Exception {

        presenter = new DetailRecipePresenter(view, interactorDB, interactor);
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
    public void doLoadRecipe() {
        presenter.doLoadRecipe(12);

        verify(interactor, times(1)).getRecipe(12, presenter);
    }

    @Test
    public void doSummarize() {
        presenter.doSummarize(12);

        verify(interactor, times(1)).getSummary(12, presenter);
    }


    @Test
    public void doFindSimilar() {
        presenter.doFindSimilar(12);

        verify(interactor, times(1)).getSimilar(12, presenter);
    }

    @Test
    public void doInstructions() {
        presenter.doInstructions(12);

        verify(interactor, times(1)).getInstructions(12, presenter);
    }

    @Test
    public void doShowContent() {
        presenter.doShowContent();

        verify(view, times(1)).showContent(true);
        verify(view, times(1)).showLoader(false);
    }

    @Test
    public void doDeleteRecipe() {

        RecipeModel model = new RecipeModel();

        presenter.doDeleteRecipe(model);

        verify(interactorDB, times(1)).removeRecipe(model);
    }

    @Test
    public void doSaveRecipe() {
        RecipeModel model = new RecipeModel();

        presenter.doSaveRecipe(model);

        verify(interactorDB, times(1)).addRecipe(model);
    }

    @Test
    public void onReceivedGetRecipe() {

        RecipeModel model = new RecipeModel();

        presenter.onReceived(model, DetailRecipePresenter.CALL_TYPE.GET_RECIPE);

        verify(view, times(1)).setRecipe(model);
    }

    @Test
    public void onReceivedSummarize() {

        SummaryModel model = new SummaryModel();

        presenter.onReceived(model, DetailRecipePresenter.CALL_TYPE.SUMMARIZE);

        verify(view, times(1)).setSummary(model);
    }

    @Test
    public void onReceivedInstruction() {

        List<RecipesModel> model = new ArrayList<RecipesModel>();

        presenter.onReceived(model, DetailRecipePresenter.CALL_TYPE.SIMILAR);

        verify(view, times(1)).setSimilar(model);
    }

    @Test
    public void doSetRecipeIconTypeVeryPopular() {

        RecipeModel model = new RecipeModel();
        model.setVeryPopular(true);
        model.setReadyInMinutes(0);
        model.setVegetarian(true);

        presenter.doSetRecipeIconType(model);

        verify(view, times(1)).showRecipePriceOrPopular("Very popular!", false, true);
    }

    @Test
    public void doSetRecipeIconTypeVEGETARIAN() {

        RecipeModel model = new RecipeModel();
        model.setVeryPopular(false);
        model.setPricePerServing(1000);
        model.setReadyInMinutes(0);
        model.setVegetarian(true);

        presenter.doSetRecipeIconType(model);


        verify(view, times(1)).setRecipeTypeIcon(DetailRecipeActivity.ICON_TYPE.VEGETARIAN, true);
    }
}