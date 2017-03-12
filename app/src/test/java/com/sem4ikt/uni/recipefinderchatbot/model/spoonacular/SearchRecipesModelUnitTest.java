package com.sem4ikt.uni.recipefinderchatbot.model.spoonacular;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mathiaslykkepedersen on 11/03/2017.
 */

public class SearchRecipesModelUnitTest {

    private SearchRecipesModel searchModel = null;

    @Before
    public void setUp() {
        searchModel = new SearchRecipesModel();
    }

    @Test
    public void setNumber(){

        searchModel.setNumber(1);

        Assert.assertEquals((int) searchModel.getNumber(), 1);
    }

    @Test
    public void setExpires(){

        searchModel.setExpires(1);

        Assert.assertEquals((int) searchModel.getExpires(), 1);
    }

    @Test
    public void setOffset(){

        searchModel.setOffset(1);

        Assert.assertEquals((int) searchModel.getOffset(), 1);
    }

    @Test
    public void setTotalResults(){

        searchModel.setTotalResults(1);

        Assert.assertEquals((int) searchModel.getTotalResults(), 1);
    }

    @Test
    public void setProcessingTimeMs(){

        searchModel.setProcessingTimeMs(1);

        Assert.assertEquals((int) searchModel.getProcessingTimeMs(), 1);
    }

    @Test
    public void setBaseUri(){

        searchModel.setBaseUri("test");

        Assert.assertEquals(searchModel.getBaseUri(), "test");
    }

    @Test
    public void setIsStale(){

        searchModel.setIsStale(true);

        Assert.assertEquals(searchModel.getIsStale(), true);
    }

    @Test
    public void setResults(){


        List<RecipesModel> list = new ArrayList<>();

        RecipesModel model = new RecipesModel();

        model.setTitle("test");

        list.add(model);

        searchModel.setResults(list);

        Assert.assertEquals(searchModel.getResults(), list);
    }
}
