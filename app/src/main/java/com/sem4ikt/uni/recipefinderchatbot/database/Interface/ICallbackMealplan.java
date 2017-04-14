package com.sem4ikt.uni.recipefinderchatbot.database.Interface;

import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * Created by anton on 11-04-2017.
 */

public interface ICallbackMealplan {

    void onReceived(Object mealplan, List<Date> dateList, MEALPLAN_CALLBACK_TYPE type);

    enum MEALPLAN_CALLBACK_TYPE{GET_MEALPLAN};
}
