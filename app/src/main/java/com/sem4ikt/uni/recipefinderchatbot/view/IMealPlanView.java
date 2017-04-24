package com.sem4ikt.uni.recipefinderchatbot.view;

import com.sem4ikt.uni.recipefinderchatbot.model.spoonacular.MealPlanDayModel;
import com.sem4ikt.uni.recipefinderchatbot.model.spoonacular.MealPlanWeekModel;

import java.util.Date;
import java.util.List;

/**
 * Created by mathiaslykkepedersen on 27/03/2017.
 */

public interface IMealPlanView {

    void getDayPlan(List<MealPlanDayModel> mealplan, List<Date>dates);
    void getWeekPlan(List<MealPlanWeekModel> mealplan,List<Date> dates);

    void onNextPressed();

    void onPreviousPressed();
}
