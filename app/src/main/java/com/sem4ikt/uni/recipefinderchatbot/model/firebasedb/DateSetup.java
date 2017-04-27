package com.sem4ikt.uni.recipefinderchatbot.model.firebasedb;

import android.support.annotation.VisibleForTesting;

import com.sem4ikt.uni.recipefinderchatbot.model.firebasedb.interfaces.IDateSetup;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by anton on 15-04-2017.
 */

public class DateSetup implements IDateSetup {

    public DateModel setDateModelWeek(Date startdate)
    {
        Date trimstartdate  = trim(startdate);
        Date enddate = setEndDate(trimstartdate,6);
        return new DateModel(trimstartdate.getTime(),enddate.getTime());
    }

    public DateModel setDateModelDay(Date startdate)
    {
        Date trimstartdate = trim(startdate);
        return new DateModel(trimstartdate.getTime(),trimstartdate.getTime());
    }

    private Date trim(Date startdate)
    {
        Calendar c = Calendar.getInstance();
        c.setTime(startdate);
        c.set(Calendar.MILLISECOND,0);
        c.set(Calendar.SECOND,0);
        c.set(Calendar.MINUTE,0);
        c.set(Calendar.HOUR_OF_DAY,12);
        return c.getTime();
    }

    private Date setEndDate(Date startdate, int duration)
    {
        Calendar c = Calendar.getInstance();
        c.setTime(startdate);
        c.add(Calendar.DATE,duration);
        return c.getTime();

    }
}
