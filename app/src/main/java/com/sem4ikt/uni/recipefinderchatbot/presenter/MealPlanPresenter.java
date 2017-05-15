package com.sem4ikt.uni.recipefinderchatbot.presenter;

import android.support.annotation.VisibleForTesting;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.sem4ikt.uni.recipefinderchatbot.database.Interface.ICallbackDayMealplan;
import com.sem4ikt.uni.recipefinderchatbot.database.Interface.ICallbackWeekMealplan;
import com.sem4ikt.uni.recipefinderchatbot.database.Interface.IFirebaseDBInteractors;
import com.sem4ikt.uni.recipefinderchatbot.database.MealPlansInteractor;
import com.sem4ikt.uni.recipefinderchatbot.model.spoonacular.MealPlanDayModel;
import com.sem4ikt.uni.recipefinderchatbot.model.spoonacular.MealPlanWeekModel;
import com.sem4ikt.uni.recipefinderchatbot.presenter.interfaces.IMealPlanPresenter;
import com.sem4ikt.uni.recipefinderchatbot.view.IMealPlanView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/*
  Created by mathiaslykkepedersen on 27/03/2017.
 */

public class MealPlanPresenter extends BasePresenter<IMealPlanView> implements IMealPlanPresenter<IMealPlanView>,ICallbackDayMealplan,ICallbackWeekMealplan {
    public List<Date> daysWithMealplan, weeksWithMealplan;
    public List<MealPlanWeekModel> weekPlans;
    public List<MealPlanDayModel> dayPlans;
    public int dayInWeek = 0;
    public boolean dayplanActive;
    public int planIndex;

    private IFirebaseDBInteractors.IMealplanInteractor ctrl;

    public MealPlanPresenter(IMealPlanView view, IFirebaseDBInteractors.IMealplanInteractor ctrl) {
        super(view);
        this.ctrl = ctrl;
        daysWithMealplan = new ArrayList<>();
        weeksWithMealplan = new ArrayList<>();
        weekPlans = new ArrayList<>();
        dayPlans = new ArrayList<>();
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
        cal.set(Calendar.MILLISECOND,0);
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
    public int getID(int id){
        if (dayplanActive) {
            id = dayPlans.get(planIndex).getRecipeModels().get(id).getId();
        }
        else {

            String value = weekPlans.get(planIndex).getItems().get(dayInWeek*3+id).getValue();
            id = new JsonParser().parse(value).getAsJsonObject().get("id").getAsInt();
        }
        return id;
    }
    @Override
    public void InitDayPlans(List<MealPlanDayModel> mealplan,List<Date> dates){
        daysWithMealplan.addAll(dates);
        dayPlans.addAll(mealplan);
    }
    @Override
    public void InitWeekPlans(List<MealPlanWeekModel> mealplan,List<Date> dates){
        weeksWithMealplan.addAll(dates);
        weekPlans.addAll(mealplan);
    }

    @Override
    public void showNoPlan(){
        view.showNoPlan();
    }

    @Override
    public void showMealplanForDay(String[] imageURLs){
        view.showPlan();
        view.insertPictures(imageURLs);
    }

    @Override
    public void doLunch() {
        view.onShowDetailRecipe(1);
    }

    @Override
    public void doDinner() {
        view.onShowDetailRecipe(2);
    }

    @Override
    public String[] loadMealplans(Date selectedDate){
        // Pass the data
        String image="0";
        String[] URLs= new String[3];
        String value;
        JsonObject jon;
        boolean beenInDay = false;
        boolean containsPlan = false;
        selectedDate = setDateToTwelve(selectedDate);

        Date mealPlanStart = selectedDate;
        int meal = 0;


        //breakfast
        if(daysWithMealplan!=null ) {
            if (daysWithMealplan.contains(selectedDate)) {
                planIndex = daysWithMealplan.indexOf(selectedDate);
                image = dayPlans.get(planIndex).getRecipeModels().get(meal).getImage();//if dayplan

                beenInDay = true;
                meal++;

            }
        }
        if (!beenInDay&& weeksWithMealplan != null) {
            for (dayInWeek = 0; dayInWeek < 7; dayInWeek++) {
                mealPlanStart = decrementDay(selectedDate,dayInWeek);
                containsPlan = weeksWithMealplan.contains(mealPlanStart);//find out if contains plan and find day in week
                if (containsPlan) {
                    break;
                }
            }
        }
        if (containsPlan ) {
            planIndex = weeksWithMealplan.indexOf(mealPlanStart);
            meal=dayInWeek*3;
            if(weekPlans.get(planIndex).getItems().size()<21){
                while((weekPlans.get(planIndex).getItems().size()-3)<meal){
                    meal--;
                }
            }
            value = weekPlans.get(planIndex).getItems().get(meal).getValue();
            jon = new JsonParser().parse(value).getAsJsonObject();
            image = jon.get("id").getAsString();
            image = image + "-556x370.jpg";                             //create picture UR
            meal++;
        }

        else if(!beenInDay){
                return URLs=null;               //if no plan exists for day
        }

        if(image!=null)
        {
            String BASE_URL = "https://spoonacular.com/recipeImages/";
            String imageUrl;

            if (image.contains("https"))
                imageUrl = image;
            else
                imageUrl = BASE_URL + image;//insert picture

            URLs[0]=imageUrl;
        }

        //lunch
        if (beenInDay) {
            planIndex = daysWithMealplan.indexOf(selectedDate);
            image = dayPlans.get(planIndex).getRecipeModels().get(meal).getImage();//if dayplan
            meal++;
        }
        else if (containsPlan) {
            planIndex = weeksWithMealplan.indexOf(mealPlanStart);
            value = weekPlans.get(planIndex).getItems().get(meal).getValue();
            jon = new JsonParser().parse(value).getAsJsonObject();
            image = jon.get("id").getAsString();
            image = image + "-556x370.jpg";                             //create picture URL
            meal++;
        }

        if(image!=null) {
            String BASE_URL = "https://spoonacular.com/recipeImages/";
            String imageUrl;

            if (image.contains("https"))
                imageUrl = image;
            else
                imageUrl = BASE_URL + image;//insert picture

            URLs[1]=imageUrl;
        }

        //dinner
        if (beenInDay) {
            planIndex = daysWithMealplan.indexOf(selectedDate);
            image = dayPlans.get(planIndex).getRecipeModels().get(meal).getImage();//if daypla
        }
        else if (containsPlan) {
            planIndex = weeksWithMealplan.indexOf(mealPlanStart);
            value = weekPlans.get(planIndex).getItems().get(meal).getValue();
            jon = new JsonParser().parse(value).getAsJsonObject();
            image = jon.get("id").getAsString();
            image = image + "-556x370.jpg";                             //create picture URL

        }

        if(image!=null)
        {
            String BASE_URL = "https://spoonacular.com/recipeImages/";
            String imageUrl;

            if (image.contains("https"))
                imageUrl = image;
            else
                imageUrl = BASE_URL + image;//insert picture

            URLs[2]=imageUrl;
        }
        dayplanActive=beenInDay;
        return URLs;
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
    public void onReceivedDay(List<MealPlanDayModel> daymodel, List<Date> datelist, MealPlanDayCallbackType type){
        switch (type)
        {
            case SUCCCES:
                if (daymodel != null && datelist != null) {
                    view.getDayPlan(daymodel, datelist);
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void onFailureDay() {
        view.showErrorToast();
    }

    @Override
    public void onReceivedWeek(List<MealPlanWeekModel> weekmodel, List<Date> datelist, MealPlanWeekCallbackType type) {
        switch (type)
        {
            case SUCCCES:
                if(weekmodel != null && datelist != null) {
                    view.getWeekPlan(weekmodel, datelist);
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void onFailureWeek() {
        view.showErrorToast();
    }
}
