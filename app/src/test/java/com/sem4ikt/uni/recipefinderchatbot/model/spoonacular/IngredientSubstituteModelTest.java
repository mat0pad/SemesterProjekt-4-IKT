package com.sem4ikt.uni.recipefinderchatbot.model.spoonacular;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.ArrayList;
/**
 * Created by anton on 11-03-2017.
 */

public class IngredientSubstituteModelTest {
    private IngredientSubstituteModel ingredientSubstituteModel = null;

    @Before
    public void setUp() {ingredientSubstituteModel = new IngredientSubstituteModel();}

    @Test
    public void setStatus()
    {
        ingredientSubstituteModel.setStatus("teststatus");
        Assert.assertEquals(ingredientSubstituteModel.getStatus(),"teststatus");
    }

    @Test
    public void setIngredient()
    {
        ingredientSubstituteModel.setIngredient("testingredient");
        Assert.assertEquals(ingredientSubstituteModel.getIngredient(),"testingredient");
    }

    @Test
    public void setMessage()
    {
        ingredientSubstituteModel.setMessage("testmessage");
        Assert.assertEquals(ingredientSubstituteModel.getMessage(),"testmessage");
    }

    @Test
    public void setSubstitutes()
    {
        List<String> list = new ArrayList();
        list.add("Item 1");
        list.add("Item 2");
        ingredientSubstituteModel.setSubstitutes(list);
        Assert.assertEquals(ingredientSubstituteModel.getSubstitutes(), list);
    }

}
