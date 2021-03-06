package com.sem4ikt.uni.recipefinderchatbot.model.spoonacular;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by anton on 11-03-2017.
 */

public class NutrientModelTest {
    /**
     DELTA is the max delta between excepted
     and actual number are still considered equal
     */
    private final double DELTA = 1e-15;
    private NutrientModel nutrientModel = null;

    @Before
    public void setUp() {
        nutrientModel = new NutrientModel();
    }

    @Test
    public void setCalories()
    {
        nutrientModel.setCalories(1.0);
        Assert.assertEquals(nutrientModel.getCalories(), 1.0, DELTA);
    }

    @Test
    public void setProtein()
    {
        nutrientModel.setProtein(1.02);
        Assert.assertEquals(nutrientModel.getProtein(),1.02,DELTA);
    }

    @Test
    public void setFat()
    {
        nutrientModel.setFat(1.02);
        Assert.assertEquals(nutrientModel.getFat(),1.02,DELTA);
    }

    @Test
    public void setCarbonhydrates()
    {
        nutrientModel.setCarbohydrates(1.02);
        Assert.assertEquals(nutrientModel.getCarbohydrates(),1.02,DELTA);
    }
}
