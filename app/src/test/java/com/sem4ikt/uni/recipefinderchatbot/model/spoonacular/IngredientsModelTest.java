package com.sem4ikt.uni.recipefinderchatbot.model.spoonacular;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
/**
 * Created by anton on 11-03-2017.
 */

public class IngredientsModelTest {

    private IngredientsModel IngredientModel = null;

    @Before
    public void setUp(){
        IngredientModel = new IngredientsModel();}

    @Test
    public void setId()
    {
        IngredientModel.setId(1);
        Assert.assertEquals((int) IngredientModel.getId(),1);
    }

    @Test
    public void setTitle()
    {
        IngredientModel.setTitle("TestTitle");
        Assert.assertEquals(IngredientModel.getTitle(),"TestTitle");
    }

    @Test
    public void setImage()
    {
        IngredientModel.setImage("TestImage");
        Assert.assertEquals(IngredientModel.getImage(),"TestImage");
    }

    @Test
    public void setUsedIngredientCount()
    {
        IngredientModel.setUsedIngredientCount(1);
        Assert.assertEquals((int) IngredientModel.getUsedIngredientCount(),1);
    }

    @Test
    public void setMissedIngredientCount()
    {
        IngredientModel.setMissedIngredientCount(1);
        Assert.assertEquals((int) IngredientModel.getMissedIngredientCount(),1);
    }

    @Test
    public void setLikes()
    {
        IngredientModel.setLikes(1);
        Assert.assertEquals((int) IngredientModel.getLikes(),1);
    }
}
