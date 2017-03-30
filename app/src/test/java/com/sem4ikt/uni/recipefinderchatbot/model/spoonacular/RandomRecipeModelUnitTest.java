package com.sem4ikt.uni.recipefinderchatbot.model.spoonacular;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mathiaslykkepedersen on 30/03/2017.
 */

public class RandomRecipeModelUnitTest {

    private RandomRecipeModel model = null;

    @Before
    public void setUp() {
        model = new RandomRecipeModel();
    }

    @Test
    public void setRandomRecipeList() {
        RecipeModel recipeModel = new RecipeModel();
        recipeModel.setImage("test");

        List<RecipeModel> list = new ArrayList<>();
        list.add(recipeModel);

        model.setRecipesList(list);
        Assert.assertEquals(model.getRecipesList(), list);
    }

}
