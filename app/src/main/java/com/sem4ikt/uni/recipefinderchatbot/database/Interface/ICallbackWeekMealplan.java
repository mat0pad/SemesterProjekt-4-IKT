package com.sem4ikt.uni.recipefinderchatbot.database.Interface;

import com.sem4ikt.uni.recipefinderchatbot.model.spoonacular.MealPlanDayModel;
import com.sem4ikt.uni.recipefinderchatbot.model.spoonacular.MealPlanWeekModel;

import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * Created by anton on 11-04-2017.
 */

public interface ICallbackWeekMealplan {

    void onReceivedWeek(List<MealPlanWeekModel> weekmodel, List<Date> list, MealPlanWeekCallbackType type);

    void onFailureWeek();

    enum MealPlanWeekCallbackType{SUCCCES,NODATA}
}
