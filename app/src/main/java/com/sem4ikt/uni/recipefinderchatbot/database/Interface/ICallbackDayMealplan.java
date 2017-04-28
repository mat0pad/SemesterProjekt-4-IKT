package com.sem4ikt.uni.recipefinderchatbot.database.Interface;

import com.sem4ikt.uni.recipefinderchatbot.model.spoonacular.MealPlanDayModel;
import java.util.Date;
import java.util.List;

/**
 * Created by anton on 27-04-2017.
 */

public interface ICallbackDayMealplan {

    void onReceivedDay(List<MealPlanDayModel> daymodel, List<Date> dateList, MealPlanDayCallbackType type);

    void onFailureDay();

    enum MealPlanDayCallbackType{SUCCCES,NODATA};
}
