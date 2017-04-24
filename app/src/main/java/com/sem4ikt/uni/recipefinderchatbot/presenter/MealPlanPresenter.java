package com.sem4ikt.uni.recipefinderchatbot.presenter;

import android.support.annotation.VisibleForTesting;

import com.sem4ikt.uni.recipefinderchatbot.database.Interface.ICallbackMealplan;
import com.sem4ikt.uni.recipefinderchatbot.database.Interface.IFirebaseDBInteractors;
import com.sem4ikt.uni.recipefinderchatbot.database.MealPlansInteractor;
import com.sem4ikt.uni.recipefinderchatbot.model.spoonacular.MealPlanDayModel;
import com.sem4ikt.uni.recipefinderchatbot.model.spoonacular.MealPlanWeekModel;
import com.sem4ikt.uni.recipefinderchatbot.presenter.interfaces.IMealPlanPresenter;
import com.sem4ikt.uni.recipefinderchatbot.view.IMealPlanView;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/*
  Created by mathiaslykkepedersen on 27/03/2017.
 */

public class MealPlanPresenter extends BasePresenter<IMealPlanView> implements IMealPlanPresenter<IMealPlanView>,ICallbackMealplan {

    private IFirebaseDBInteractors.IMealplanInteractor ctrl;

    public MealPlanPresenter(IMealPlanView view) {
        super(view);
        ctrl = new MealPlansInteractor();
    }

    @VisibleForTesting
    public MealPlanPresenter(IMealPlanView view, IFirebaseDBInteractors.IMealplanInteractor ctrl) {
        super(view);
        this.ctrl = ctrl;
    }

    @Override
    public void getMealPlanDay(){ctrl.getMealPlanDay(this);}

    @Override
    public void getMealPlanWeek(){ctrl.getMealPlanWeek(this);}


    @Override
    public Date setDateToTwelve(Date selectedDate) {

        Calendar cal = Calendar.getInstance(Locale.GERMANY);
        cal.setTime(selectedDate);
        cal.set(Calendar.HOUR_OF_DAY,12);
        cal.set(Calendar.MINUTE,0);
        cal.set(Calendar.SECOND,0);
        return cal.getTime();

    }

    @Override
    public void doNext() {
        view.onNextPressed();
    }

    @Override
    public void doPrevious() {
        view.onPreviousPressed();
    }

    @Override
    public void doBreakfast() {
        view.onShowDetailRecipe(0);
    }

    @Override
    public void doDinner() {
        view.onShowDetailRecipe(2);
    }

    @Override
    public void doLunch() {
        view.onShowDetailRecipe(1);
    }


    public void update() {
        //ctrl.update(this);
    }

    @Override
    public Date getTime() {
        return Calendar.getInstance(Locale.GERMANY).getTime();
    }

    @Override
    public Date decrementDay(Date date, int dayInWeek) {
        Calendar cal = Calendar.getInstance(Locale.GERMANY);
        cal.setTime(date);
        cal.add(Calendar.DATE, -dayInWeek);
        return cal.getTime();

    }

    @Override
    public void onReceivedDay(List<MealPlanDayModel> daymodel, List<Date> datelist, MEALPLAN_CALLBACK_TYPE type) {
        switch (type)
        {
            case GET_MEALPLAN_DAY:
                if (daymodel != null && datelist != null) {
                    view.getDayPlan(daymodel, datelist);
                }
                break;

            case MEALPLAN_FAILURE:
                break;
            default:
                break;
        }
    }

    @Override
    public void onReceivedWeek(List<MealPlanWeekModel> weekmodel, List<Date> datelist, MEALPLAN_CALLBACK_TYPE type) {

        switch (type)
        {
            case GET_MEALPLAN_WEEK:
                if(weekmodel != null && datelist != null) {
                    view.getWeekPlan(weekmodel, datelist);
                }
                break;
            case MEALPLAN_FAILURE:

                break;
            default:
                break;
        }
    }
}
