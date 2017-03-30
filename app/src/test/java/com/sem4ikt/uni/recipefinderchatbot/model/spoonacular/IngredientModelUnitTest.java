package com.sem4ikt.uni.recipefinderchatbot.model.spoonacular;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by mathiaslykkepedersen on 30/03/2017.
 */

public class IngredientModelUnitTest {

    private IngredientModel model = null;

    @Before
    public void setUp() {
        model = new IngredientModel();
    }

    @Test
    public void setName() {
        model.setName("test");
        Assert.assertEquals(model.getName(), "test");
    }

    @Test
    public void setImage() {
        model.setImage("test");
        Assert.assertEquals(model.getImage(), "test");
    }

    @Test
    public void setInclude() {
        model.setInclude(false);
        Assert.assertEquals(model.getInclude(), false);
    }

}
