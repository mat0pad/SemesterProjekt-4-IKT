package com.sem4ikt.uni.recipefinderchatbot.model.spoonacular;

import com.sem4ikt.uni.recipefinderchatbot.model.ExtendedIngredientModel;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mathiaslykkepedersen on 08/03/2017.
 */
public class ExtendedIngredientModelUnitTest {

    private ExtendedIngredientModel extendedIngredientModel = null;

    @Before
    public void setUp() {
        extendedIngredientModel = new ExtendedIngredientModel();
    }

    @Test
    public void setId(){

        extendedIngredientModel.setId(1);

        Assert.assertEquals((int) extendedIngredientModel.getId(), 1);
    }

    @Test
    public void setAisle(){

        extendedIngredientModel.setAisle("test");

        Assert.assertEquals( extendedIngredientModel.getAisle(), "test");
    }

    @Test
    public void setName(){

        extendedIngredientModel.setName("test");

        Assert.assertEquals( extendedIngredientModel.getName(), "test");
    }

    @Test
    public void setAmount(){

        extendedIngredientModel.setAmount(12.0);

        Assert.assertEquals(extendedIngredientModel.getAmount(), 12.0, 0);
    }

    @Test
    public void setImage(){

        extendedIngredientModel.setImage("test");

        Assert.assertEquals( extendedIngredientModel.getImage(), "test");
    }

    @Test
    public void setUnit(){

        extendedIngredientModel.setUnit("test");

        Assert.assertEquals( extendedIngredientModel.getUnit(), "test");
    }

    @Test
    public void setOriginal(){

        extendedIngredientModel.setOriginalString("test");

        Assert.assertEquals( extendedIngredientModel.getOriginalString(), "test");
    }

    @Test
    public void setUnitLong(){

        extendedIngredientModel.setUnitLong("test");

        Assert.assertEquals( extendedIngredientModel.getUnitLong(), "test");
    }

    @Test
    public void setUnitShort(){

        extendedIngredientModel.setUnitShort("test");

        Assert.assertEquals( extendedIngredientModel.getUnitShort(), "test");
    }

    @Test
    public void setMetaInformation(){

        List<String> list = new ArrayList<String>();

        list.add("test1");
        list.add("test2");

        extendedIngredientModel.setMetaInformation(list);

        Assert.assertEquals( extendedIngredientModel.getMetaInformation(), list);
    }

}
