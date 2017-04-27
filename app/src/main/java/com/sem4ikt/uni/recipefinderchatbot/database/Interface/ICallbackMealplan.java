package com.sem4ikt.uni.recipefinderchatbot.database.Interface;

import com.sem4ikt.uni.recipefinderchatbot.model.spoonacular.MealPlanDayModel;
import com.sem4ikt.uni.recipefinderchatbot.model.spoonacular.MealPlanWeekModel;

import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * Created by anton on 11-04-2017.
 */

public interface ICallbackMealplan {

    void onReceivedDay(List<MealPlanDayModel> daymodel, List<Date> dateList, MEALPLAN_CALLBACK_TYPE type);

    void onReceivedWeek(List<MealPlanWeekModel> weekmodel, List<Date> list, MEALPLAN_CALLBACK_TYPE type);

    enum MEALPLAN_CALLBACK_TYPE{GET_MEALPLAN_DAY,GET_MEALPLAN_WEEK,MEALPLAN_FAILURE};
}
