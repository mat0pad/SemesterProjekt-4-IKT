package com.sem4ikt.uni.recipefinderchatbot.presenter;

import com.sem4ikt.uni.recipefinderchatbot.database.Interface.IFirebaseDBInteractors;
import com.sem4ikt.uni.recipefinderchatbot.view.IMealPlanView;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * Created by mathiaslykkepedersen on 30/03/2017.
 */

@RunWith(MockitoJUnitRunner.class)
public class MealPlanPresenterUnitTest {

    @Mock
    IMealPlanView mealPlanView;
    @Mock
    IFirebaseDBInteractors.IMealplanInteractor interactor;
    private MealPlanPresenter presenter;

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

    /*@Test
    public  void onReceivedValidMealPlanWeek(){
        presenter.onReceivedWeek(new ArrayList<MealPlanWeekModel>(),new ArrayList<Date>(), ICallbackMealplan.MEALPLAN_CALLBACK_TYPE.GET_MEALPLAN_WEEK);
        verify(mealPlanView,times(1)).getWeekPlan(new ArrayList<MealPlanWeekModel>(),new ArrayList<Date>());
    }

    @Test
    public  void onReceivedValidMealPlanDay(){
        presenter.onReceivedDay(new ArrayList<MealPlanDayModel>(),new ArrayList<Date>(), ICallbackMealplan.MEALPLAN_CALLBACK_TYPE.GET_MEALPLAN_DAY);
        verify(mealPlanView,times(1)).getDayPlan(new ArrayList<MealPlanDayModel>(),new ArrayList<Date>());
    }

    @Test
    public  void onReceivedNothingMealPlanWeek(){
        presenter.onReceivedWeek(null,null, ICallbackMealplan.MEALPLAN_CALLBACK_TYPE.GET_MEALPLAN_WEEK);
        verify(mealPlanView,times(0)).getWeekPlan(null,null);
    }

    @Test
    public  void onReceivedNothingMealPlanDay(){
        presenter.onReceivedDay(null,null, ICallbackMealplan.MEALPLAN_CALLBACK_TYPE.GET_MEALPLAN_DAY);
        verify(mealPlanView,times(0)).getDayPlan(null,null);
    }*/

}