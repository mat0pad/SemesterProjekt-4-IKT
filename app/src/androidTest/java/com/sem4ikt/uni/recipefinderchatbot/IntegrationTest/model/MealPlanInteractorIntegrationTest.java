package com.sem4ikt.uni.recipefinderchatbot.IntegrationTest.model;

import com.sem4ikt.uni.recipefinderchatbot.database.Authentication;
import com.sem4ikt.uni.recipefinderchatbot.database.Interface.ICallbackMealPlanAdd;
import com.sem4ikt.uni.recipefinderchatbot.database.Interface.ICallbackMealplan;
import com.sem4ikt.uni.recipefinderchatbot.database.MealPlansInteractor;
import com.sem4ikt.uni.recipefinderchatbot.model.spoonacular.MealPlanDayModel;
import com.sem4ikt.uni.recipefinderchatbot.model.spoonacular.MealPlanWeekModel;
import com.sem4ikt.uni.recipefinderchatbot.model.spoonacular.NutrientModel;
import com.sem4ikt.uni.recipefinderchatbot.presenter.LoginPresenter;
import com.sem4ikt.uni.recipefinderchatbot.presenter.interfaces.ILoginCallback;

import org.junit.Before;
import org.junit.Test;

import java.util.Date;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import static junit.framework.Assert.assertTrue;

/**
 * Created by henriknielsen on 23/04/2017.
 */


public class MealPlanInteractorIntegrationTest {

    private List<MealPlanDayModel> mealPlanDayList;
    private List<MealPlanWeekModel> mealPlanWeekList;

    private CountDownLatch signal;
    private MealPlansInteractor mpi = new MealPlansInteractor();


    @Before
    public void setup() throws InterruptedException {
        signal = new CountDownLatch(1);
        Authentication auth = new Authentication();

        auth.signIn("201507091@post.au.dk", "test123", new ILoginCallback() {
            @Override
            public void onAuthenticationFinished(LoginPresenter.AUTH auth, String reason) {
                signal.countDown();
            }
        });

        signal.await();
    }

    @Test
    public void mealPlanDayModel() throws InterruptedException {

        signal = new CountDownLatch(2);

        MealPlanDayModel planmodel = new MealPlanDayModel();
        NutrientModel nmodel = new NutrientModel();
        nmodel.setCalories(1000d);

        planmodel.setNutrients(nmodel);

        mpi.addMealPlanDay(planmodel, new Date(), new ICallbackMealPlanAdd() {
            @Override
            public void onReceived(ADD_CALLBACK_TYPE type) {
                signal.countDown();
            }
        });

        mpi.getMealPlanDay(new ICallbackMealplan() {
            @Override
            public void onReceivedDay(List<MealPlanDayModel> daymodel, List<Date> dateList, MEALPLAN_CALLBACK_TYPE type) {
                mealPlanDayList = daymodel;
                signal.countDown();
            }

            @Override
            public void onReceivedWeek(List<MealPlanWeekModel> weekmodel, List<Date> list, MEALPLAN_CALLBACK_TYPE type) {

            }
        });

        signal.await();

        assertTrue(mealPlanDayList.size() > 0);
    }


    @Test
    public void mealPlanWeekModel() throws InterruptedException {

        signal = new CountDownLatch(1);

        mpi.getMealPlanDay(new ICallbackMealplan() {
            @Override
            public void onReceivedDay(List<MealPlanDayModel> daymodel, List<Date> dateList, MEALPLAN_CALLBACK_TYPE type) {

            }

            @Override
            public void onReceivedWeek(List<MealPlanWeekModel> weekmodel, List<Date> list, MEALPLAN_CALLBACK_TYPE type) {
                mealPlanWeekList = weekmodel;
                signal.countDown();
            }
        });

        signal.await();

        assertTrue(mealPlanWeekList.size() > 0);
    }
}