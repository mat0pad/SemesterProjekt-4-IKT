package com.sem4ikt.uni.recipefinderchatbot.model;

import com.sem4ikt.uni.recipefinderchatbot.model.firebasedb.DateModel;
import com.sem4ikt.uni.recipefinderchatbot.model.firebasedb.DateSetup;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by anton on 17-04-2017.
 */

public class DateSetupTest {
    private DateSetup dateSetup;

    @Before
    public void setup()
    {
        dateSetup = new DateSetup();
    }

    @Test
    public void setDateModelWeekStartDateTest()
    {
        Date date = new Date();
        DateModel dateModelfromset = dateSetup.setDateModelWeek(date);
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.HOUR_OF_DAY,12);
        c.set(Calendar.SECOND,0);
        c.set(Calendar.MINUTE,0);
        c.set(Calendar.MILLISECOND,0);

        Assert.assertEquals(c.getTime().getTime(),dateModelfromset.startDate);
    }

    @Test
    public void setDateModelWeekEndTest()
    {
        Date date = new Date();
        DateModel dateModelfromset = dateSetup.setDateModelWeek(date);
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.HOUR_OF_DAY,12);
        c.set(Calendar.SECOND,0);
        c.set(Calendar.MINUTE,0);
        c.set(Calendar.MILLISECOND,0);

        c.add(Calendar.DATE,6);

        Assert.assertEquals(c.getTime().getTime(),dateModelfromset.endDate);
    }

    @Test
    public void setDateModelDayStartTest()
    {
        Date date = new Date();
        DateModel dateModelfromset = dateSetup.setDateModelDay(date);
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.HOUR_OF_DAY,12);
        c.set(Calendar.SECOND,0);
        c.set(Calendar.MINUTE,0);
        c.set(Calendar.MILLISECOND,0);

        Assert.assertEquals(c.getTime().getTime(),dateModelfromset.startDate);

    }

    @Test
    public void setDateModelDayEndTest()
    {
        Date date = new Date();
        DateModel dateModelfromset = dateSetup.setDateModelDay(date);
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.HOUR_OF_DAY,12);
        c.set(Calendar.SECOND,0);
        c.set(Calendar.MINUTE,0);
        c.set(Calendar.MILLISECOND,0);

        Assert.assertEquals(c.getTime().getTime(),dateModelfromset.endDate);

    }
}
