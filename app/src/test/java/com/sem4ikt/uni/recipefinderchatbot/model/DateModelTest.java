package com.sem4ikt.uni.recipefinderchatbot.model;

import com.sem4ikt.uni.recipefinderchatbot.model.firebasedb.DateModel;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by anton on 23-04-2017.
 */

public class DateModelTest {

    public DateModel dm;

    @Before
    public void setup()
    {
        dm = new DateModel();
    }

    @Test
    public void startDateTest()
    {
        long time = 102039120123L;
        dm.startDate = time;
        Assert.assertEquals(time,dm.startDate);
    }

    @Test
    public void endDateTest()
    {
        long time = 102039120123L;
        dm.endDate = time;
        Assert.assertEquals(time,dm.endDate);
    }
}
