package com.sem4ikt.uni.recipefinderchatbot.model.spoonacular;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by mathiaslykkepedersen on 11/03/2017.
 */

public class TextModelUnitTest {

    private TextModel textModel = null;

    @Before
    public void setUp() {
        textModel = new TextModel();
    }

    @Test
    public void setText(){

        textModel.setText("test");

        Assert.assertEquals(textModel.getText(), "test");
    }
}
