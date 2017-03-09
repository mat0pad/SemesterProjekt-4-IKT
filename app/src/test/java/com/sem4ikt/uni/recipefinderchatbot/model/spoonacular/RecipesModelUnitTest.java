package com.sem4ikt.uni.recipefinderchatbot.model.spoonacular;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mathiaslykkepedersen on 08/03/2017.
 */
public class RecipesModelUnitTest {

    private RecipesModel recipesModel = null;

    @Before
    public void setUp() {
        recipesModel = new RecipesModel();
    }

    @Test
    public void setId() {
        recipesModel.setId(1);

        Assert.assertEquals((int) recipesModel.getId(), 1);
    }

    @Test
    public void setTitle() {
        recipesModel.setTitle("test");

        Assert.assertEquals(recipesModel.getTitle(), "test");
    }

    @Test
    public void setImage() {
        recipesModel.setImage("test");

        Assert.assertEquals(recipesModel.getImage(), "test");
    }

    @Test
    public void setReadyInMintes() {
        recipesModel.setReadyInMinutes(30);

        Assert.assertEquals( (int) recipesModel.getReadyInMinutes(), 30);
    }

    @Test
    public void setImageUrls() {

        List<String> list = new ArrayList<String>();

        list.add("test1");
        list.add("test2");

        recipesModel.setImageUrls(list);

        Assert.assertEquals(recipesModel.getImageUrls(), list);

    }
}
