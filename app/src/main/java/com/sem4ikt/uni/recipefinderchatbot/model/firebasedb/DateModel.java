package com.sem4ikt.uni.recipefinderchatbot.model.firebasedb;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by anton on 13-04-2017.
 */

public class DateModel {

    public long endDate;
    public long startDate;

    public DateModel() {
    }


    public DateModel(long startDate, long endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }



}
