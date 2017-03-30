package com.sem4ikt.uni.recipefinderchatbot.model.spoonacular;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by mathiaslykkepedersen on 30/03/2017.
 */

public class DishModelUnitTest {

    private DishModel model = null;

    @Before
    public void setUp() {
        model = new DishModel();
    }

    @Test
    public void setName() {
        model.setName("test");
        Assert.assertEquals(model.getName(), "test");
    }

    @Test
    public void setImage() {
        model.setImage("http://randomPic.jpg");
        Assert.assertEquals(model.getImage(), "http://randomPic.jpg");
    }

}