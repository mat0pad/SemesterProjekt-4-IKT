package com.sem4ikt.uni.recipefinderchatbot.model.firebasedb.interfaces;

import com.sem4ikt.uni.recipefinderchatbot.model.firebasedb.DateModel;

import java.util.Date;

/**
 * Created by anton on 27-04-2017.
 */

public interface IDateSetup {

    DateModel setDateModelWeek(Date startdate);

    DateModel setDateModelDay(Date startdate);
}
