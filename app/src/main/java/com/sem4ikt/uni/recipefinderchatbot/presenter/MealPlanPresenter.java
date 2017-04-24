package com.sem4ikt.uni.recipefinderchatbot.presenter;

import android.support.annotation.VisibleForTesting;

import com.sem4ikt.uni.recipefinderchatbot.database.Interface.ICallbackMealplan;
import com.sem4ikt.uni.recipefinderchatbot.database.Interface.IFirebaseDBInteractors;
import com.sem4ikt.uni.recipefinderchatbot.database.MealPlansInteractor;
import com.sem4ikt.uni.recipefinderchatbot.model.spoonacular.MealPlanDayModel;
import com.sem4ikt.uni.recipefinderchatbot.model.spoonacular.MealPlanWeekModel;
import com.sem4ikt.uni.recipefinderchatbot.presenter.interfaces.IMealPlanPresenter;
import com.sem4ikt.uni.recipefinderchatbot.view.IMealPlanView;

import java.util.Date;
import java.util.List;

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

    public void update() {
        //ctrl.update(this);
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
