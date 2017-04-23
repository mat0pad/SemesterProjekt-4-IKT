package com.sem4ikt.uni.recipefinderchatbot.presenter;

import com.sem4ikt.uni.recipefinderchatbot.database.Interface.ICallbackMealplan;
import com.sem4ikt.uni.recipefinderchatbot.database.Interface.IFirebaseAuth;
import com.sem4ikt.uni.recipefinderchatbot.database.Interface.IFirebaseDBInteractors;
import com.sem4ikt.uni.recipefinderchatbot.model.spoonacular.MealPlanDayModel;
import com.sem4ikt.uni.recipefinderchatbot.model.spoonacular.MealPlanWeekModel;
import com.sem4ikt.uni.recipefinderchatbot.view.IMealPlanView;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Date;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * Created by mathiaslykkepedersen on 30/03/2017.
 */

@RunWith(MockitoJUnitRunner.class)
public class MealPlanPresenterUnitTest {

    @Mock
    IMealPlanView mealPlanView;

    private MealPlanPresenter presenter;

    @Mock
    IFirebaseDBInteractors.IMealplanInteractor interactor;


    @Before
    public void setup() throws Exception {

        presenter = new MealPlanPresenter(mealPlanView,interactor);
    }

    @Test
    public void clearViewOnDestroy() {
        presenter.clearView();

        Assert.assertEquals(presenter.getView(), null);
    }

    @Test
    public void setView() {
        presenter.clearView();
        presenter.setView(mealPlanView);

        Assert.assertEquals(presenter.getView(), mealPlanView);
    }

    @Test
    public void getMealplanDayTest(){
        presenter.getMealPlanDay();
        verify(interactor,times(1)).getMealPlanDay(presenter);
    }

    @Test
    public void getMealplanWeekTest(){
        presenter.getMealPlanWeek();
        verify(interactor,times(1)).getMealPlanWeek(presenter);
    }

    @Test
    public  void onReceivedValidMealPlanWeek(){
        presenter.onReceived(new ArrayList<MealPlanWeekModel>(),new ArrayList<Date>(), ICallbackMealplan.MEALPLAN_CALLBACK_TYPE.GET_MEALPLAN_WEEK);
        verify(mealPlanView,times(1)).getWeekPlan(new ArrayList<MealPlanWeekModel>(),new ArrayList<Date>());
    }

    @Test
    public  void onReceivedValidMealPlanDay(){
        presenter.onReceived(new ArrayList<MealPlanDayModel>(),new ArrayList<Date>(), ICallbackMealplan.MEALPLAN_CALLBACK_TYPE.GET_MEALPLAN_DAY);
        verify(mealPlanView,times(1)).getDayPlan(new ArrayList<MealPlanDayModel>(),new ArrayList<Date>());
    }

    @Test
    public  void onReceivedNothingMealPlanWeek(){
        presenter.onReceived(null,null, ICallbackMealplan.MEALPLAN_CALLBACK_TYPE.GET_MEALPLAN_WEEK);
        verify(mealPlanView,times(0)).getWeekPlan(null,null);
    }

    @Test
    public  void onReceivedNothingMealPlanDay(){
        presenter.onReceived(null,null, ICallbackMealplan.MEALPLAN_CALLBACK_TYPE.GET_MEALPLAN_DAY);
        verify(mealPlanView,times(0)).getDayPlan(null,null);
    }

}