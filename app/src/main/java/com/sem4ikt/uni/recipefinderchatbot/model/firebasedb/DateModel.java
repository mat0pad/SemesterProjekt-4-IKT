package com.sem4ikt.uni.recipefinderchatbot.model.firebasedb;

/**
 * Created by anton on 13-04-2017.
 */

public class DateModel {

    public long startDate;
    public long endDate;

    public DateModel() {}

    public DateModel(long startDate,long endDate)
    {
        this.startDate = startDate;
        this.endDate = endDate;
    }
}
