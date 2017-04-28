package com.sem4ikt.uni.recipefinderchatbot.IntegrationTest.model;

import com.sem4ikt.uni.recipefinderchatbot.database.Authentication;
import com.sem4ikt.uni.recipefinderchatbot.database.DeleteInfoInteractor;
import com.sem4ikt.uni.recipefinderchatbot.database.Interface.ICallbackDayMealplan;
import com.sem4ikt.uni.recipefinderchatbot.database.Interface.ICallbackMealPlanAdd;
import com.sem4ikt.uni.recipefinderchatbot.database.Interface.ICallbackWeekMealplan;
import com.sem4ikt.uni.recipefinderchatbot.database.MealPlansInteractor;
import com.sem4ikt.uni.recipefinderchatbot.model.spoonacular.MealPlanDayModel;
import com.sem4ikt.uni.recipefinderchatbot.model.spoonacular.MealPlanWeekModel;
import com.sem4ikt.uni.recipefinderchatbot.model.spoonacular.NutrientModel;
import com.sem4ikt.uni.recipefinderchatbot.presenter.LoginPresenter;
import com.sem4ikt.uni.recipefinderchatbot.presenter.interfaces.ILoginCallback;

import org.junit.Assert;
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
        DeleteInfoInteractor info = new DeleteInfoInteractor();
        info.removeAllUserInfo();
    }

    @Test
    public void mealPlanDayModel() throws InterruptedException {

        signal = new CountDownLatch(1);
        final CountDownLatch signaladd = new CountDownLatch(1);

        MealPlanDayModel planmodel = new MealPlanDayModel();
        NutrientModel nmodel = new NutrientModel();
        nmodel.setCalories(1000d);

        planmodel.setNutrients(nmodel);

        mpi.addMealPlanDay(planmodel, new Date(), new ICallbackMealPlanAdd() {
            @Override
            public void onReceivedMealPlanAdd() {
                signaladd.countDown();
            }

            @Override
            public void onFailureMealPlanAdd() {

            }
        });

        signaladd.await();


        mpi.getMealPlanDay(new ICallbackDayMealplan() {
            @Override
            public void onReceivedDay(List<MealPlanDayModel> daymodel, List<Date> dateList, MealPlanDayCallbackType type) {
                mealPlanDayList = daymodel;
                signal.countDown();
            }

            @Override
            public void onFailureDay() {
                signal.countDown();
            }
        });

        signal.await();

        assertTrue(mealPlanDayList.size() > 0);
    }


    @Test
    public void mealPlanWeekModelNoItems() throws InterruptedException {

        signal = new CountDownLatch(1);

        mpi.getMealPlanWeek(new ICallbackWeekMealplan() {
            @Override
            public void onReceivedWeek(List<MealPlanWeekModel> weekmodel, List<Date> list, MealPlanWeekCallbackType type) {
                mealPlanWeekList = weekmodel;
                signal.countDown();
            }

            @Override
            public void onFailureWeek() {

            }
        });

        signal.await();

        Assert.assertEquals(mealPlanWeekList,null);
    }

    @Test
    public void mealPlanWeekModelItem() throws InterruptedException {

        signal = new CountDownLatch(1);
        final CountDownLatch signalget = new CountDownLatch(1);

        MealPlanWeekModel mealplan = new MealPlanWeekModel();

        mealplan.setName("test");

        mpi.addMealPlanWeek(mealplan, new Date(), new ICallbackMealPlanAdd() {
            @Override
            public void onReceivedMealPlanAdd() {
                signal.countDown();
            }

            @Override
            public void onFailureMealPlanAdd() {

            }
        });

        signal.await();

        mpi.getMealPlanWeek(new ICallbackWeekMealplan() {
            @Override
            public void onReceivedWeek(List<MealPlanWeekModel> weekmodel, List<Date> list, MealPlanWeekCallbackType type) {
                mealPlanWeekList = weekmodel;
                signalget.countDown();
            }

            @Override
            public void onFailureWeek() {

            }
        });

        signalget.await();

        assertTrue(mealPlanWeekList.size() > 0);
    }
}