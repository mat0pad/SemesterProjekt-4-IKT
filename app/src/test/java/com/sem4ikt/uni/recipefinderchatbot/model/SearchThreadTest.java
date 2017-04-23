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

public class SearchThreadTest {

    private SearchThread st;
    List<RecipeModel> list;

    @Before
    public void setup()
    {
        list = new ArrayList<>();
    }

    public void setupthread(String searchword)
    {
        RecipeModel rp = new RecipeModel();
        String title = "cupcake";
        rp.setTitle(title);
        list.add(rp);
        String[] stringlist = new String[1];
        stringlist[0] = searchword;
        st = new SearchThread(list,stringlist);
    }

    @Test
    public void searchFoundTest()
    {
        setupthread("cupcake");
        st.search();
        List<RecipeModel> searchlist = st.getList();
        Assert.assertEquals(searchlist,list);
    }

    @Test
    public void searchNotFoundTest()
    {
        setupthread("test");
        st.search();
        List<RecipeModel> searchlist = st.getList();
        Assert.assertNotEquals(searchlist,list);
    }
}
