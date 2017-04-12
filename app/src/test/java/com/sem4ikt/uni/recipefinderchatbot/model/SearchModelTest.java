package com.sem4ikt.uni.recipefinderchatbot.model;

import com.sem4ikt.uni.recipefinderchatbot.model.spoonacular.RecipeModel;

import org.apache.commons.lang3.time.StopWatch;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by anton on 07-04-2017.
 */

public class SearchModelTest {

    private SearchModel sm;


    @Before
    public void setup()
    {
        sm = new SearchModel();
    }

    @Test
    public void singleThreadSearchFoundRecipe()
    {
        //Setup
        List<RecipeModel> list = new ArrayList<>();
        RecipeModel rp = new RecipeModel();
        rp.setTitle("Hamburger");
        list.add(rp);


        Assert.assertEquals(rp,sm.searchSingleThread(list,"Hamburger").get(0));
    }

    @Test
    public void singleThreadSearchFoundNone()
    {
        List<RecipeModel> list = new ArrayList<>();
        RecipeModel rp = new RecipeModel();
        rp.setTitle("Hamburger");
        list.add(rp);

        Assert.assertNotEquals(list.size(),sm.searchSingleThread(list,"Chicken").size());
    }

    @Test
    public void multiThreadSearchFoundRecipe() throws InterruptedException {
        //Setup

        List<RecipeModel> list = new ArrayList<>();
        RecipeModel rp = new RecipeModel();
        rp.setTitle("Hamburger");
        list.add(rp);

        Assert.assertEquals(rp,sm.searchMultiThread(list,"Hamburger").get(0));

    }

    @Test
    public void multiThreadSearchFoundNone() throws InterruptedException {
        List<RecipeModel> list = new ArrayList<>();
        RecipeModel rp = new RecipeModel();
        rp.setTitle("Hamburger");
        list.add(rp);

        Assert.assertNotEquals(list.size(),sm.searchMultiThread(list,"Chicken").size());

    }
}
