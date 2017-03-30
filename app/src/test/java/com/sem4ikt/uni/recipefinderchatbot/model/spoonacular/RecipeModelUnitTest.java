package com.sem4ikt.uni.recipefinderchatbot.model.spoonacular;

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
    public void setId() {
        recipeModel.setId(1);

        Assert.assertEquals((int) recipeModel.getId(), 1);
    }

    @Test
    public void setTitle() {
        recipeModel.setTitle("test");

        Assert.assertEquals(recipeModel.getTitle(), "test");
    }

    @Test
    public void setImage() {
        recipeModel.setImage("test");

        Assert.assertEquals(recipeModel.getImage(), "test");
    }

    @Test
    public void setReadyInMintes() {
        recipeModel.setReadyInMinutes(30);

        Assert.assertEquals( (int) recipeModel.getReadyInMinutes(), 30);
    }

    @Test
    public void setImageType() {
        recipeModel.setImageType("test");

        Assert.assertEquals( recipeModel.getImageType(), "test");
    }

    @Test
    public void setSourceName(){
        recipeModel.setSourceName("test");

        Assert.assertEquals( recipeModel.getSourceName(), "test");
    }

    @Test
    public void setCreditText(){
        recipeModel.setCreditText("test");

        Assert.assertEquals( recipeModel.getCreditText(), "test");
    }

    @Test
    public void setSpoonacularSourceUrl(){
        recipeModel.setSpoonacularSourceUrl("test");

        Assert.assertEquals( recipeModel.getSpoonacularSourceUrl(), "test");
    }

    @Test
    public void setGaps(){
        recipeModel.setGaps("test");

        Assert.assertEquals( recipeModel.getGaps(), "test");
    }

    @Test
    public void setSourceUrl(){
        recipeModel.setSourceUrl("test");

        Assert.assertEquals( recipeModel.getSourceUrl(), "test");
    }

    @Test
    public void setAggregateLikes(){
        recipeModel.setAggregateLikes(12);

        Assert.assertEquals( (int) recipeModel.getAggregateLikes(), 12);
    }

    @Test
    public void setServings(){
        recipeModel.setServings(12);

        Assert.assertEquals( (int) recipeModel.getServings(), 12);
    }

    @Test
    public void setWeightWatcherSmartPoints(){
        recipeModel.setWeightWatcherSmartPoints(12);

        Assert.assertEquals( (int) recipeModel.getWeightWatcherSmartPoints(), 12);
    }

    @Test
    public void setInstructions(){
        recipeModel.setInstructions("test");

        Assert.assertEquals( recipeModel.getInstructions(), "test");
    }

    @Test
    public void setExtendedIngredients(){

        ExtendedIngredientModel ingredient = new ExtendedIngredientModel();
        ingredient.setImage("test");

        List<ExtendedIngredientModel> list = new ArrayList<>();

        list.add(ingredient);

        recipeModel.setExtendedIngredients(list);

        Assert.assertEquals( recipeModel.getExtendedIngredients(), list);
    }



    /**-------- Properties test - Booleans---------**/

    @Test
    public void setCheap() {
        recipeModel.setCheap(true);

        Assert.assertEquals( recipeModel.getCheap(), true);
    }

    @Test
    public void setVegan() {
        recipeModel.setVegan(true);

        Assert.assertEquals( recipeModel.getVegan(), true);
    }

    @Test
    public void setVegetarian() {
        recipeModel.setVegetarian(true);

        Assert.assertEquals( recipeModel.getVegetarian(), true);
    }

    @Test
    public void setDairyFree() {
        recipeModel.setDairyFree(true);

        Assert.assertEquals( recipeModel.getDairyFree(), true);
    }

    @Test
    public void setVeryHealthy() {
        recipeModel.setVeryHealthy(true);

        Assert.assertEquals( recipeModel.getVeryHealthy(), true);
    }

    @Test
    public void setVeryPopular() {
        recipeModel.setVeryPopular(true);

        Assert.assertEquals( recipeModel.getVeryPopular(), true);
    }

    @Test
    public void setSustainable() {
        recipeModel.setSustainable(true);

        Assert.assertEquals( recipeModel.getSustainable(), true);
    }

    @Test
    public void setGlutenFree() {
        recipeModel.setGlutenFree(true);

        Assert.assertEquals( recipeModel.getGlutenFree(), true);
    }

    @Test
    public void setKetogenic() {
        recipeModel.setKetogenic(true);

        Assert.assertEquals( recipeModel.getKetogenic(), true);
    }

    @Test
    public void setLowFodmap() {
        recipeModel.setLowFodmap(true);

        Assert.assertEquals( recipeModel.getLowFodmap(), true);
    }

    @Test
    public void setWhole30() {
        recipeModel.setWhole30(true);

        Assert.assertEquals( recipeModel.getWhole30(), true);
    }

    @Test
    public void setPricePerServing() {
        recipeModel.setPricePerServing(23);

        Assert.assertEquals(recipeModel.getPricePerServing(), 23, 23);
    }

    @Test
    public void setHealthScore() {
        recipeModel.setHealthScore(23);

        Assert.assertEquals(recipeModel.getHealthScore(), 23, 23);
    }

    @Test
    public void setSpoonacularScore() {
        recipeModel.setSpoonacularScore(23);

        Assert.assertEquals(recipeModel.getSpoonacularScore(), 23, 23);
    }
}
