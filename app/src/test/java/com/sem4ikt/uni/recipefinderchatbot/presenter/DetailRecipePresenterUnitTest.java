package com.sem4ikt.uni.recipefinderchatbot.presenter;

import com.sem4ikt.uni.recipefinderchatbot.activity.DetailRecipeActivity;
import com.sem4ikt.uni.recipefinderchatbot.database.Interface.IFirebaseDBInteractors;
import com.sem4ikt.uni.recipefinderchatbot.model.interfaces.IDetailRecipeInteractor;
import com.sem4ikt.uni.recipefinderchatbot.model.spoonacular.EquipmentModel;
import com.sem4ikt.uni.recipefinderchatbot.model.spoonacular.InstructionsModel;
import com.sem4ikt.uni.recipefinderchatbot.model.spoonacular.RecipeModel;
import com.sem4ikt.uni.recipefinderchatbot.model.spoonacular.RecipesModel;
import com.sem4ikt.uni.recipefinderchatbot.model.spoonacular.StepModel;
import com.sem4ikt.uni.recipefinderchatbot.model.spoonacular.SummaryModel;
import com.sem4ikt.uni.recipefinderchatbot.presenter.interfaces.IDetailRecipeCallback;
import com.sem4ikt.uni.recipefinderchatbot.view.IDetailRecipeView;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyBoolean;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.atMost;
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
    public void onReceivedSimilar() {

        List<RecipesModel> model = new ArrayList<RecipesModel>();

        presenter.onReceived(model, DetailRecipePresenter.CALL_TYPE.SIMILAR);

        verify(view, times(1)).setSimilar(model);
    }

    @Test
    public void onReceivedInstruction()
    {
        List<InstructionsModel> model = new ArrayList<>();
        List<StepModel> list = new ArrayList<>();
        StepModel sm = new StepModel();
        sm.setStep("test");

        list.add(sm);

        InstructionsModel ele = new InstructionsModel();
        ele.setSteps(list);

        model.add(ele);

        presenter.onReceived(model, DetailRecipePresenter.CALL_TYPE.INSTRUCTION);

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

    @Test
    public void doSetRecipeIconTypeVegan()
    {
        RecipeModel model = new RecipeModel();
        model.setVeryPopular(false);
        model.setPricePerServing(1000);
        model.setReadyInMinutes(0);
        model.setVegan(true);
        model.setVegetarian(false);

        presenter.doSetRecipeIconType(model);


        verify(view, times(1)).setRecipeTypeIcon(DetailRecipeActivity.ICON_TYPE.VEGAN, true);
    }

    @Test
    public void doSetRecipeIconTypeWhole30()
    {
        RecipeModel model = new RecipeModel();
        model.setVeryPopular(false);
        model.setPricePerServing(1000);
        model.setReadyInMinutes(0);
        model.setVegan(false);
        model.setVegetarian(false);
        model.setWhole30(true);

        presenter.doSetRecipeIconType(model);


        verify(view, times(1)).setRecipeTypeIcon(DetailRecipeActivity.ICON_TYPE.WHOLE30, true);
    }

    @Test
    public void doSetRecipeIconTypePaleo()
    {
        RecipeModel model = new RecipeModel();
        model.setVeryPopular(false);
        model.setPricePerServing(1000);
        model.setReadyInMinutes(0);
        model.setVegan(false);
        model.setVegetarian(false);
        model.setWhole30(false);
        model.setLowFodmap(true);

        presenter.doSetRecipeIconType(model);


        verify(view, times(1)).setRecipeTypeIcon(DetailRecipeActivity.ICON_TYPE.PALEO, true);
    }

    @Test
    public void doSetRecipeIconTypeOrganic()
    {
        RecipeModel model = new RecipeModel();
        model.setVeryPopular(false);
        model.setPricePerServing(1000);
        model.setReadyInMinutes(0);
        model.setVegan(false);
        model.setVegetarian(false);
        model.setWhole30(false);
        model.setLowFodmap(false);
        model.setSustainable(true);

        presenter.doSetRecipeIconType(model);


        verify(view, times(1)).setRecipeTypeIcon(DetailRecipeActivity.ICON_TYPE.ORGANIC, true);
    }

    @Test
    public void doSetRecipeIconTypeGlutenFree()
    {
        RecipeModel model = new RecipeModel();
        model.setVeryPopular(false);
        model.setPricePerServing(1000);
        model.setReadyInMinutes(0);
        model.setVegan(false);
        model.setVegetarian(false);
        model.setWhole30(false);
        model.setLowFodmap(false);
        model.setSustainable(false);
        model.setGlutenFree(true);

        presenter.doSetRecipeIconType(model);


        verify(view, times(1)).setRecipeTypeIcon(DetailRecipeActivity.ICON_TYPE.GLUTEN_FREE, true);
    }

    @Test
    public void doSetRecipeIconTypeKetogenic()
    {
        RecipeModel model = new RecipeModel();
        model.setVeryPopular(false);
        model.setPricePerServing(1000);
        model.setReadyInMinutes(0);
        model.setVegan(false);
        model.setVegetarian(false);
        model.setWhole30(false);
        model.setLowFodmap(false);
        model.setSustainable(false);
        model.setGlutenFree(false);
        model.setKetogenic(true);

        presenter.doSetRecipeIconType(model);


        verify(view, times(1)).setRecipeTypeIcon(DetailRecipeActivity.ICON_TYPE.KETOGENIC, true);
    }

    @Test
    public void doSetRecipeIconTypeVeryHealthy()
    {
        RecipeModel model = new RecipeModel();
        model.setVeryPopular(false);
        model.setPricePerServing(1000);
        model.setReadyInMinutes(0);
        model.setVegan(false);
        model.setVegetarian(false);
        model.setWhole30(false);
        model.setLowFodmap(false);
        model.setSustainable(false);
        model.setGlutenFree(false);
        model.setKetogenic(false);
        model.setVeryHealthy(true);

        presenter.doSetRecipeIconType(model);


        verify(view, times(1)).setRecipeTypeIcon(DetailRecipeActivity.ICON_TYPE.WEIGHT_WATCH, true);
    }

    @Test
    public void doSetRecipeIconTypeCallView()
    {
        int value = 10;
        String PricInDollar = ",10";

        RecipeModel recipe = new RecipeModel();
        recipe.setVeryPopular(false);
        recipe.setPricePerServing(10);
        recipe.setReadyInMinutes(new Integer(0));
        recipe.setVegetarian(true);

        presenter.doSetRecipeIconType(recipe);

        verify(view,times(1)).showRecipePriceOrPopular("$"+PricInDollar+ " per serving",true,true);
    }

    @Test
    public void doSetRecipeIconTypePopular()
    {
        int value = 10;
        String PricInDollar = ",10";

        RecipeModel recipe = new RecipeModel();
        recipe.setVeryPopular(true);
        recipe.setReadyInMinutes(new Integer(0));
        recipe.setVegetarian(true);

        presenter.doSetRecipeIconType(recipe);

        verify(view,times(1)).showRecipePriceOrPopular("Very popular!",false,true);

    }

    @Test
    public void doSetRecipeIconTypeMinutes()
    {
        RecipeModel recipe = new RecipeModel();
        recipe.setVeryPopular(true);
        recipe.setReadyInMinutes(new Integer(10));
        recipe.setVegetarian(true);

        presenter.doSetRecipeIconType(recipe);

        verify(view,times(1)).showRecipeTime(anyString(),anyBoolean());

    }


}