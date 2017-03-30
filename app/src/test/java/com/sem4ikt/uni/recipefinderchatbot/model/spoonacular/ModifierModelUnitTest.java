package com.sem4ikt.uni.recipefinderchatbot.model.spoonacular;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by mathiaslykkepedersen on 30/03/2017.
 */

public class ModifierModelUnitTest {

    private ModifierModel model = null;

    @Before
    public void setUp() {
        model = new ModifierModel();
    }

    @Test
    public void setName() {
        model.setName("test");
        Assert.assertEquals(model.getName(), "test");
    }

}
