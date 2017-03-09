package com.sem4ikt.uni.recipefinderchatbot.model.spoonacular;

import com.sem4ikt.uni.recipefinderchatbot.model.ExtendedIngredientModel;
import com.sem4ikt.uni.recipefinderchatbot.model.RecipeModel;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mathiaslykkepedersen on 08/03/2017.
 */
public class RecipeModelUnitTest {

    private RecipeModel recipeModel = null;

    @Before
    public void setUp() {
        recipeModel = new RecipeModel();
    }

    @Test
    public void Set_Id() {
        recipeModel.setId(1);

        Assert.assertEquals((int) recipeModel.getId(), 1);
    }

    @Test
    public void Set_Title() {
        recipeModel.setTitle("test");

        Assert.assertEquals(recipeModel.getTitle(), "test");
    }

    @Test
    public void Set_Image() {
        recipeModel.setImage("test");

        Assert.assertEquals(recipeModel.getImage(), "test");
    }

    @Test
    public void Set_ReadyInMintes() {
        recipeModel.setReadyInMinutes(30);

        Assert.assertEquals( (int) recipeModel.getReadyInMinutes(), 30);
    }

    @Test
    public void Set_ImageType() {
        recipeModel.setImageType("test");

        Assert.assertEquals( recipeModel.getImageType(), "test");
    }

    @Test
    public void Set_SourceName(){
        recipeModel.setSourceName("test");

        Assert.assertEquals( recipeModel.getSourceName(), "test");
    }

    @Test
    public void Set_CreditText(){
        recipeModel.setCreditText("test");

        Assert.assertEquals( recipeModel.getCreditText(), "test");
    }

    @Test
    public void Set_SpoonacularSourceUrl(){
        recipeModel.setSpoonacularSourceUrl("test");

        Assert.assertEquals( recipeModel.getSpoonacularSourceUrl(), "test");
    }

    @Test
    public void Set_Gaps(){
        recipeModel.setGaps("test");

        Assert.assertEquals( recipeModel.getGaps(), "test");
    }

    @Test
    public void Set_SourceUrl(){
        recipeModel.setSourceUrl("test");

        Assert.assertEquals( recipeModel.getSourceUrl(), "test");
    }

    @Test
    public void Set_AggregateLikes(){
        recipeModel.setAggregateLikes(12);

        Assert.assertEquals( (int) recipeModel.getAggregateLikes(), 12);
    }

    @Test
    public void Set_Servings(){
        recipeModel.setServings(12);

        Assert.assertEquals( (int) recipeModel.getServings(), 12);
    }

    @Test
    public void Set_WeightWatcherSmartPoints(){
        recipeModel.setWeightWatcherSmartPoints(12);

        Assert.assertEquals( (int) recipeModel.getWeightWatcherSmartPoints(), 12);
    }

    @Test
    public void Set_Instructions(){
        recipeModel.setInstructions("test");

        Assert.assertEquals( recipeModel.getInstructions(), "test");
    }

    @Test
    public void Set_ExtendedIngredients(){

        ExtendedIngredientModel ingredient = new ExtendedIngredientModel();
        ingredient.setImage("test");

        List<ExtendedIngredientModel> list = new ArrayList<>();

        list.add(ingredient);

        recipeModel.setExtendedIngredients(list);

        Assert.assertEquals( recipeModel.getExtendedIngredients(), list);
    }



    /**-------- Properties test - Booleans---------**/

    @Test
    public void Set_Cheap() {
        recipeModel.setCheap(true);

        Assert.assertEquals( recipeModel.getCheap(), true);
    }

    @Test
    public void Set_Vegan() {
        recipeModel.setVegan(true);

        Assert.assertEquals( recipeModel.getVegan(), true);
    }

    @Test
    public void Set_Vegetarian() {
        recipeModel.setVegetarian(true);

        Assert.assertEquals( recipeModel.getVegetarian(), true);
    }

    @Test
    public void Set_DairyFree() {
        recipeModel.setDairyFree(true);

        Assert.assertEquals( recipeModel.getDairyFree(), true);
    }

    @Test
    public void Set_VeryHealthy() {
        recipeModel.setVeryHealthy(true);

        Assert.assertEquals( recipeModel.getVeryHealthy(), true);
    }

    @Test
    public void Set_VeryPopular() {
        recipeModel.setVeryPopular(true);

        Assert.assertEquals( recipeModel.getVeryPopular(), true);
    }

    @Test
    public void Set_Sustainable() {
        recipeModel.setSustainable(true);

        Assert.assertEquals( recipeModel.getSustainable(), true);
    }

    @Test
    public void Set_GlutenFree() {
        recipeModel.setGlutenFree(true);

        Assert.assertEquals( recipeModel.getGlutenFree(), true);
    }

    @Test
    public void Set_Ketogenic() {
        recipeModel.setKetogenic(true);

        Assert.assertEquals( recipeModel.getKetogenic(), true);
    }

    @Test
    public void Set_LowFodmap() {
        recipeModel.setLowFodmap(true);

        Assert.assertEquals( recipeModel.getLowFodmap(), true);
    }

    @Test
    public void Set_Whole30() {
        recipeModel.setWhole30(true);

        Assert.assertEquals( recipeModel.getWhole30(), true);
    }

}
