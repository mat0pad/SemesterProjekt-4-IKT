package com.sem4ikt.uni.recipefinderchatbot.model.firebasedb;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by anton on 15-04-2017.
 */

public class DateSetup {

    public DateModel SetDateModelWeek(Date startdate)
    {
        Calendar c = Calendar.getInstance();
        c.setTime(startdate);
        c.set(Calendar.HOUR_OF_DAY,12);
        long start = c.getTime().getTime();
        c.add(Calendar.DATE,6);
        return new DateModel(start,c.getTime().getTime());
    }

    public DateModel setDateModelDay(Date startdate)
    {
        Calendar c = Calendar.getInstance();
        c.setTime(startdate);
        c.set(Calendar.HOUR_OF_DAY,12);
        long start = c.getTime().getTime();
        return new DateModel(start,start);
    }
}
