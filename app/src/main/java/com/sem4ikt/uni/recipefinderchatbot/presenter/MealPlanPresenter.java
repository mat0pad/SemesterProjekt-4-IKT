package com.sem4ikt.uni.recipefinderchatbot.presenter;

import com.sem4ikt.uni.recipefinderchatbot.database.Interface.ICallbackMealplan;
import com.sem4ikt.uni.recipefinderchatbot.database.MealPlansInteractor;
import com.sem4ikt.uni.recipefinderchatbot.model.spoonacular.MealPlanDayModel;
import com.sem4ikt.uni.recipefinderchatbot.model.spoonacular.MealPlanWeekModel;
import com.sem4ikt.uni.recipefinderchatbot.presenter.interfaces.IMealPlanPresenter;
import com.sem4ikt.uni.recipefinderchatbot.view.IMealPlanView;

import java.util.Date;
import java.util.List;

/**
 * Created by mathiaslykkepedersen on 27/03/2017.
 */

public class MealPlanPresenter extends BasePresenter<IMealPlanView> implements IMealPlanPresenter<IMealPlanView>,ICallbackMealplan {
    private MealPlansInteractor ctrl;
   public MealPlanPresenter(IMealPlanView view) {
        super(view);
       ctrl= new MealPlansInteractor();
    }

    public void getMealPlanDay(){ctrl.getMealPlanDay(this);}
    public void getMealPlanWeek(){ctrl.getMealPlanWeek(this);}
    public void update(){//ctrl.update(this);
        }



    @Override
    public void onReceived(Object mealplan, List<Date> date, MEALPLAN_CALLBACK_TYPE type) {
        switch (type) {
            case GET_MEALPLAN_DAY:
                view.getDayPlan();
                break;

            case GET_MEALPLAN_WEEK:
                view.getWeekPlan();
                break;
        }

    }
}
