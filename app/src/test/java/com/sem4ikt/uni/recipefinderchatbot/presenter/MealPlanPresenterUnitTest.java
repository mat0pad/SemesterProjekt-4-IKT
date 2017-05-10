package com.sem4ikt.uni.recipefinderchatbot.presenter;

import com.sem4ikt.uni.recipefinderchatbot.database.Interface.ICallbackDayMealplan;
import com.sem4ikt.uni.recipefinderchatbot.database.Interface.ICallbackWeekMealplan;

import com.sem4ikt.uni.recipefinderchatbot.database.Interface.IFirebaseDBInteractors;
import com.sem4ikt.uni.recipefinderchatbot.model.spoonacular.Item;
import com.sem4ikt.uni.recipefinderchatbot.model.spoonacular.MealPlanDayModel;
import com.sem4ikt.uni.recipefinderchatbot.model.spoonacular.MealPlanWeekModel;
import com.sem4ikt.uni.recipefinderchatbot.model.spoonacular.RecipesModel;
import com.sem4ikt.uni.recipefinderchatbot.view.IMealPlanView;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * Created by mathiaslykkepedersen on 30/03/2017.
 */

@RunWith(MockitoJUnitRunner.class)
public class MealPlanPresenterUnitTest {

    @Mock
    IMealPlanView view;
    @Mock
    IFirebaseDBInteractors.IMealplanInteractor interactor;
    private MealPlanPresenter presenter;

    @Before
    public void setup() throws Exception {
        presenter = new MealPlanPresenter(view, interactor);
    }

    @Test
    public void clearViewOnDestroy() {
        presenter.clearView();

        Assert.assertEquals(presenter.getView(), null);
    }

    @Test
    public void setView() {
        presenter.clearView();
        presenter.setView(view);

        Assert.assertEquals(presenter.getView(), view);
    }

    @Test
    public void getMealplanDayTest() {
        presenter.getMealPlanDay();
        verify(interactor, times(1)).getMealPlanDay(presenter);
    }

    @Test
    public void getMealplanWeekTest() {
        presenter.getMealPlanWeek();
        verify(interactor, times(1)).getMealPlanWeek(presenter);
    }

    @Test
    public void setDateToTwelveTest() {
        Date dato = new Date();
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 12);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.MILLISECOND, 0);
        Assert.assertEquals(presenter.setDateToTwelve(dato).getTime(), cal.getTime().getTime());
    }


    public void loadMealplansTestWithNonValidInfo() {
        Assert.assertNull(presenter.loadMealplans(new Date()));
    }

    @Test
    public  void onReceivedValidMealPlanWeek(){
        presenter.onReceivedWeek(new ArrayList<MealPlanWeekModel>(),new ArrayList<Date>(), ICallbackWeekMealplan.MealPlanWeekCallbackType.SUCCCES);
        verify(view,times(1)).getWeekPlan(new ArrayList<MealPlanWeekModel>(),new ArrayList<Date>());
    }

    @Test
    public  void onReceivedValidMealPlanDay(){
        presenter.onReceivedDay(new ArrayList<MealPlanDayModel>(),new ArrayList<Date>(), ICallbackDayMealplan.MealPlanDayCallbackType.SUCCCES);
        verify(view,times(1)).getDayPlan(new ArrayList<MealPlanDayModel>(),new ArrayList<Date>());
    }

    @Test
    public  void onReceivedNothingMealPlanWeek(){
        presenter.onReceivedWeek(null,null, ICallbackWeekMealplan.MealPlanWeekCallbackType.NODATA);
        verify(view,times(0)).getWeekPlan(null,null);
    }

    @Test
    public  void onReceivedNothingMealPlanDay(){
        presenter.onReceivedDay(null,null, ICallbackDayMealplan.MealPlanDayCallbackType.NODATA);
        verify(view,times(0)).getDayPlan(null,null);
    }

    @Test
    public void doNextViewCallTest()
    {
        presenter.doNext();

        verify(view,times(1)).onNextPressed();
    }

    @Test
    public void doPreviousViewCallTest()
    {
        presenter.doPrevious();

        verify(view,times(1)).onPreviousPressed();
    }

    @Test
    public void doBreakFastViewCallTest()
    {
        presenter.doBreakfast();

        verify(view,times(1)).onShowDetailRecipe(0);
    }

    @Test
    public void initDayPlansTest()
    {
        MealPlanDayModel mealplan = new MealPlanDayModel();
        Date date = new Date();
        List<MealPlanDayModel> listMealplan = new ArrayList<MealPlanDayModel>();
        List<Date> listDate = new ArrayList<>();
        listMealplan.add(mealplan);
        listDate.add(date);


        presenter.InitDayPlans(listMealplan,listDate);

        Assert.assertTrue(presenter.dayPlans.get(0) == listMealplan.get(0) && presenter.daysWithMealplan.get(0) == listDate.get(0));
    }

    @Test
    public void initWeekPlansTest()
    {
        MealPlanWeekModel mealplan = new MealPlanWeekModel();
        Date date = new Date();
        List<MealPlanWeekModel> listMealplan = new ArrayList<>();
        List<Date> listDate = new ArrayList<>();
        listMealplan.add(mealplan);
        listDate.add(date);


        presenter.InitWeekPlans(listMealplan,listDate);

        Assert.assertTrue(presenter.weekPlans.get(0) == listMealplan.get(0) && presenter.weeksWithMealplan.get(0) == listDate.get(0));
    }

    @Test
    public void showNopLanTestView()
    {
        presenter.showNoPlan();
        verify(view,times(1)).showNoPlan();
    }

    @Test
    public void showMealPlanForDayViewCallTest()
    {
        String[] array = new String[]  {"test"};
        presenter.showMealplanForDay(array);
        verify(view,times(1)).showPlan();
        verify(view,times(1)).insertPictures(array);
    }
    @Test
    public void getIdViewCallTest()
    {
      //  TextView textView = new TextView(context);
       // presenter.showNoPlan();

       // presenter.getID(0);
    }

    @Test
    public void doLunchViewCallTest()
    {
        presenter.doLunch();

        verify(view,times(1)).onShowDetailRecipe(1);
    }

    @Test
    public void doDinnerViewCallTest()
    {
        presenter.doDinner();

        verify(view,times(1)).onShowDetailRecipe(2);
    }

    @Test
    public void loadMealplansReturnNull()
    {
        MealPlanDayModel mealplan = new MealPlanDayModel();
        Date date = new Date();
        List<MealPlanDayModel> listMealplan = new ArrayList<>();
        List<Date> listDate = new ArrayList<>();
        listMealplan.add(mealplan);
        listDate.add(date);

        presenter.InitDayPlans(listMealplan,listDate); //Dayswithmealplan and dayplans set

        Assert.assertNull(presenter.loadMealplans(new Date()));

    }


    @Test
    public void loadMealplansReturnBreakfast()
    {
        List<RecipesModel> list = new ArrayList<>();
        MealPlanDayModel mealplan = new MealPlanDayModel();
        for(int i = 0;i<3;++i)
        {
            RecipesModel rp = new RecipesModel();
            rp.setImage("test");
            list.add(rp);
        }

        mealplan.setRecipeModels(list);
        Date date = presenter.setDateToTwelve(new Date());

        List<MealPlanDayModel> listMealplan = new ArrayList<>();
        List<Date> listDate = new ArrayList<>();
        listMealplan.add(mealplan);
        listDate.add(date);

        presenter.InitDayPlans(listMealplan,listDate); //Dayswithmealplan and dayplans set

        Assert.assertNotNull(presenter.loadMealplans(date));

    }

    @Test
    public void getTimeTest()
    {
        Assert.assertNotNull(presenter.getTime());
    }

    @Test
    public void onFailureDayTest()
    {
        presenter.onFailureDay();
    }

    @Test
    public void onFailureWeekTest()
    {
        presenter.onFailureWeek();
    }

    @Test
    public void getIdMealPlanDayTest()
    {
        int id = 100;
        List<RecipesModel> list = new ArrayList<>();
        MealPlanDayModel mealplan = new MealPlanDayModel();
        for(int i = 0;i<3;++i)
        {
            RecipesModel rp = new RecipesModel();
            rp.setImage("https");
            rp.setId(id);
            list.add(rp);
        }

        mealplan.setRecipeModels(list);
        Date date = presenter.setDateToTwelve(new Date());

        List<MealPlanDayModel> listMealplan = new ArrayList<>();
        List<Date> listDate = new ArrayList<>();
        listMealplan.add(mealplan);
        listDate.add(date);

        presenter.InitDayPlans(listMealplan,listDate); //Dayswithmealplan and dayplans set

        presenter.loadMealplans(date);

        Assert.assertTrue(id == presenter.getID(0));
    }

    @Test
    public void getIdMealPlanWeekTest()
    {
        MealPlanWeekModel mealplan = new MealPlanWeekModel();
        List<Item> itemsList = new ArrayList<>();
        Item item = new Item();
        item.setValue("{\"id\":533604,\"imageType\":\"jpg\",\"title\":\"Chocolate Glazed Sour Cream Coffee Cake\"}");
        for(int i=0;i<21;++i)
        {
            itemsList.add(item);
        }

        mealplan.setItems(itemsList);
        Date date = presenter.setDateToTwelve(new Date());

        List<MealPlanWeekModel> listMealplan = new ArrayList<>();
        List<Date> listDate = new ArrayList<>();
        listMealplan.add(mealplan);
        listDate.add(date);

        presenter.InitWeekPlans(listMealplan,listDate);

        presenter.loadMealplans(date);

        Assert.assertTrue(presenter.getID(0) == 533604);
    }

    @Test
    public void loadMealplansTest()
    {
        MealPlanWeekModel mealplan = new MealPlanWeekModel();
        List<Item> itemsList = new ArrayList<>();
        Item item = new Item();
        item.setValue("{\"id\":533604,\"imageType\":\"jpg\",\"title\":\"Chocolate Glazed Sour Cream Coffee Cake\"}");
        for(int i=0;i<4;++i)
        {
            itemsList.add(item);
        }

        mealplan.setItems(itemsList);
        Date date = presenter.setDateToTwelve(new Date());

        List<MealPlanWeekModel> listMealplan = new ArrayList<>();
        List<Date> listDate = new ArrayList<>();
        listMealplan.add(mealplan);
        listDate.add(date);

        presenter.InitWeekPlans(listMealplan,listDate);

        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DATE,1);
        presenter.loadMealplans(c.getTime());

    }

    @Test
    public void LoadMealPlanImageNullTest()
    {
        presenter.loadMealplans(new Date());
    }





}