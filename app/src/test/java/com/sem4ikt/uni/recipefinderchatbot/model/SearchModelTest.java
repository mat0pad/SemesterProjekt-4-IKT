package com.sem4ikt.uni.recipefinderchatbot.model;

import com.sem4ikt.uni.recipefinderchatbot.model.spoonacular.RecipeModel;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by anton on 23-04-2017.
 */

public class SearchModelTest {

    private SearchModel sm;
    List<RecipeModel> list;

    @Before
    public void setup()
    {
        sm = new SearchModel();
        list = new ArrayList<>();
    }

    @Test
    public void searchSingleThreadFirstWordTest()
    {
        String Title = "Cupcake";
        RecipeModel rp = new RecipeModel();
        rp.setTitle(Title);
        list.add(rp);
        List<RecipeModel> searchlist = sm.searchSingleThread(list,Title);
        Assert.assertEquals(searchlist,list);
    }

    @Test
    public void searchSingleThreadNotFoundTest()
    {
        String Title = "Cupcake";
        RecipeModel rp = new RecipeModel();
        rp.setTitle(Title);
        list.add(rp);
        List<RecipeModel> searchlist = sm.searchSingleThread(list,"test");
        Assert.assertNotEquals(searchlist,list);
    }

    @Test
    public void setSingleThreadInsideTest()
    {
        String Title = "xxcupcakexx";
        RecipeModel rp = new RecipeModel();
        rp.setTitle(Title);
        list.add(rp);
        List<RecipeModel> searchlist = sm.searchSingleThread(list,"cupcake");
        Assert.assertEquals(searchlist,list);
    }


}
