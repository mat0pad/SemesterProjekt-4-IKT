package com.sem4ikt.uni.recipefinderchatbot.model.spoonacular;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by mathiaslykkepedersen on 22/04/2017.
 */

public class ItemUnitTest {

    private Item item = null;

    @Before
    public void setUp() {
        item = new Item();
    }

    @Test
    public void setDay() {

        item.setDay(0);

        Assert.assertEquals((int) item.getDay(), 0);
    }

    @Test
    public void setPosition() {

        item.setPosition(0);

        Assert.assertEquals((int) item.getPosition(), 0);
    }

    @Test
    public void setSlot() {

        item.setSlot(0);

        Assert.assertEquals((int) item.getSlot(), 0);
    }

    @Test
    public void setMealPlanId() {

        item.setMealPlanId(0);

        Assert.assertEquals((int) item.getMealPlanId(), 0);
    }

    @Test
    public void setValue() {

        item.setValue("test");

        Assert.assertEquals(item.getValue(), "test");
    }

    @Test
    public void setType() {

        item.setType("test");

        Assert.assertEquals(item.getType(), "test");
    }
}
