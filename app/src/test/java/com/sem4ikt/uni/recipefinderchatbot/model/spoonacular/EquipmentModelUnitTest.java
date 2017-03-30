package com.sem4ikt.uni.recipefinderchatbot.model.spoonacular;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by mathiaslykkepedersen on 30/03/2017.
 */

public class EquipmentModelUnitTest {

    private EquipmentModel model = null;

    @Before
    public void setUp() {
        model = new EquipmentModel();
    }

    @Test
    public void setId() {
        model.setId(1);
        Assert.assertEquals((int) model.getId(), 1);
    }

    @Test
    public void setTitle() {
        model.setName("TestTitle");
        Assert.assertEquals(model.getName(), "TestTitle");
    }

    @Test
    public void setImage() {
        model.setImage("TestImage");
        Assert.assertEquals(model.getImage(), "TestImage");
    }
}
