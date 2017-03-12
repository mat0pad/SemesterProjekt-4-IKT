package com.sem4ikt.uni.recipefinderchatbot.model.spoonacular;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by mathiaslykkepedersen on 11/03/2017.
 */

public class SummaryModelUnitTest {
    private SummaryModel summaryModel = null;

    @Before
    public void setUp() {
        summaryModel = new SummaryModel();
    }

    @Test
    public void setSummary(){

        summaryModel.setSummary("test");

        Assert.assertEquals(summaryModel.getSummary(), "test");
    }

    @Test
    public void setTitle(){

        summaryModel.setTitle("test");

        Assert.assertEquals(summaryModel.getTitle(), "test");
    }

    @Test
    public void setId(){

        summaryModel.setId(123);

        Assert.assertEquals((int)summaryModel.getId(), 123);
    }
}
