package com.sem4ikt.uni.recipefinderchatbot.presenter.interfaces;

import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.sem4ikt.uni.recipefinderchatbot.model.spoonacular.MealPlanDayModel;
import com.sem4ikt.uni.recipefinderchatbot.model.spoonacular.MealPlanWeekModel;

import java.util.Date;
import java.util.List;

/**
 * Created by mathiaslykkepedersen on 27/03/2017.
 */

public interface IMealPlanPresenter<V> extends IBasePresenter<V> {

    void getMealPlanDay();

    void getMealPlanWeek();

    void doNext();

    void doPrevious();

    Date setDateToTwelve(Date selectedDate);

    void update();

    Date getTime();

    Date decrementDay(Date date, int dayInWeek);

    void doBreakfast();

    void doDinner();

    void doLunch();

    int getID(int id);

    void InitDayPlans(List<MealPlanDayModel> mealplan, List<Date> dates);

    void InitWeekPlans(List<MealPlanWeekModel> mealplan, List<Date> dates);

    String[] loadMealplans(Date selectedDate);

    void showNoPlan(TextView noplan, ScrollView day);

    void showMealplanForDay(TextView noplan, ScrollView day, String[] imageURLs, ImageView breakfastImage, ImageView lunchImage, ImageView dinnerImage);
}
