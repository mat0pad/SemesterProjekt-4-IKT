package com.sem4ikt.uni.recipefinderchatbot.model.spoonacular;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by mathiaslykkepedersen on 11/03/2017.
 */

public class NutrientsModelUnitTest {

    private NutrientsDataModel nutrientsModel = null;

    @Before
    public void setUp() {
        nutrientsModel = new NutrientsDataModel();
    }

    @Test
    public void setTitle() {
        nutrientsModel.setTitle("test");

        Assert.assertEquals( nutrientsModel.getTitle(), "test");
    }

    @Test
    public void setId() {
        nutrientsModel.setId(123);

        Assert.assertEquals( (int) nutrientsModel.getId(), 123);
    }

    @Test
    public void setImage() {
        nutrientsModel.setImage("test");

        Assert.assertEquals( nutrientsModel.getImage(), "test");
    }

    @Test
    public void setProtein() {
        nutrientsModel.setProtein(123);

        Assert.assertEquals( (int) nutrientsModel.getProtein(), 123);
    }

    @Test
    public void setCarbs() {
        nutrientsModel.setCarbs(123);

        Assert.assertEquals( (int) nutrientsModel.getCarbs(), 123);
    }

    @Test
    public void setCalories() {
        nutrientsModel.setCalories(123);

        Assert.assertEquals( (int) nutrientsModel.getCalories(), 123);
    }

    @Test
    public void setFat() {
        nutrientsModel.setFat(123);

        Assert.assertEquals( (int) nutrientsModel.getFat(), 123);
    }

    @Test
    public void setImageType() {
        nutrientsModel.setImageType("test");

        Assert.assertEquals( nutrientsModel.getImageType(), "test");
    }
}
