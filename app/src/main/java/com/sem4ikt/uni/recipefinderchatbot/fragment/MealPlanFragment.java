package com.sem4ikt.uni.recipefinderchatbot.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.github.sundeepk.compactcalendarview.CompactCalendarView;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.sem4ikt.uni.recipefinderchatbot.R;
import com.sem4ikt.uni.recipefinderchatbot.activity.DetailRecipeActivity;
import com.sem4ikt.uni.recipefinderchatbot.database.MealPlansInteractor;
import com.sem4ikt.uni.recipefinderchatbot.model.spoonacular.MealPlanDayModel;
import com.sem4ikt.uni.recipefinderchatbot.model.spoonacular.MealPlanWeekModel;
import com.sem4ikt.uni.recipefinderchatbot.presenter.MealPlanPresenter;
import com.sem4ikt.uni.recipefinderchatbot.rest.ApiClient;
import com.sem4ikt.uni.recipefinderchatbot.rest.ISpoonacularAPI;
import com.sem4ikt.uni.recipefinderchatbot.view.IMealPlanView;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Christian on 12-03-2017.
 */

public class MealPlanFragment extends Fragment implements IMealPlanView {

    ImageView dinner;
    ImageView breakfast;
    ImageView lunch;
    ScrollView day;
    TextView noplan;
    MealPlanWeekModel weekPlan;
    MealPlanDayModel dayPlan;
    MealPlanPresenter presenter;
    private CompactCalendarView compactCalenderView;
    private ActionBar toolbar;
    private SimpleDateFormat dateFormatForMonth = new SimpleDateFormat("MMM - yyyy", Locale.getDefault());
    private Date selectedDate;
    List<Date> daysWithMealplan;
    List<Date> weeksWithMealplan;
    Calendar cal;
    int dayInWeek=0;
    Date WeekDayPassed=null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        if (container == null) {
            return null;
        }

        presenter=new MealPlanPresenter(this);
        final View view = inflater.inflate(R.layout.mealplan, container, false);

        final Button showPreviousMonthBut = (Button) view.findViewById(R.id.prev_button);
        final Button showNextMonthBut = (Button) view.findViewById(R.id.next_button);


        toolbar.setTitle(dateFormatForMonth.format(compactCalenderView.getFirstDayOfCurrentMonth()));
        selectedDate= new Date();

        dinner = (ImageView) view.findViewById(R.id.dinner);
        compactCalenderView= (CompactCalendarView) view.findViewById(R.id.compactcalendar_view);
        breakfast = (ImageView) view.findViewById(R.id.breakfast);
        lunch = (ImageView) view.findViewById(R.id.lunch);
        day= (ScrollView) view.findViewById(R.id.dayview);//visibility GONE if no plan for date
        noplan= (TextView) view.findViewById(R.id.noplan);//visibility VISIBLE if no plan for date
        presenter.getMealPlanWeek();
        presenter.getMealPlanDay();
        cal= Calendar.getInstance();


                    final Handler mainHandler = new Handler(Looper.getMainLooper());

                    final Runnable myRunnable = new Runnable() {
                        @Override
                        public void run() {
                            // Pass the data
                            String image=null;
                            String value;
                            JsonObject jon;
                            boolean beenInDay= false;
                            boolean foundweekDay=false;
                            int index;
                            int meal=0;
                            //breakfast
                            if(daysWithMealplan.contains(selectedDate)){
                                image =dayPlan.getRecipeModels().get(meal).getImage();//if dayplan
                                noplan.setVisibility(View.GONE);
                                day.setVisibility(View.VISIBLE);
                                beenInDay=true;
                                meal++;
                            }
                            if(!beenInDay) {
                                Iterator<Date> iter = weeksWithMealplan.iterator();
                                Date dato = weeksWithMealplan.get(0);
                                for (index = 0; iter.hasNext(); index++) {//if weekplan
                                    foundweekDay = dato == selectedDate;
                                    if (foundweekDay) break;//find mealplan start
                                    dato = iter.next();
                                    iter.remove();
                                }
                                if (foundweekDay) {
                                    if (selectedDate != WeekDayPassed) {
                                        value = weekPlan.getItems().get(index).getValue();
                                        dayInWeek = 0;
                                        WeekDayPassed = selectedDate;//find day of mealplan
                                    } else {
                                        dayInWeek++;
                                        value = weekPlan.getItems().get(index + dayInWeek+meal).getValue();
                                    }

                                    jon = new JsonParser().parse(value).getAsJsonObject();
                                    image = jon.get("id").getAsString();
                                    image = image + "-556x370.jpg";//create picture URL
                                    day.setVisibility(View.VISIBLE);
                                    noplan.setVisibility(View.GONE);
                                    meal++;
                                }
                            }
                            else {
                                noplan.setVisibility(View.VISIBLE);
                                day.setVisibility(View.GONE);
                            }

                            if(image!=null)
                            {
                                String BASE_URL = "https://spoonacular.com/recipeImages/";
                                String imageUrl;

                                if (image.contains("https"))
                                    imageUrl = image;
                                else
                                    imageUrl = BASE_URL + image;//insert picture

                                Picasso.with(getContext()).load(imageUrl).fit().into(breakfast);
                            }

                            //lunch
                            if(daysWithMealplan.contains(selectedDate)){
                                image =dayPlan.getRecipeModels().get(meal).getImage();
                                noplan.setVisibility(View.GONE);
                                day.setVisibility(View.VISIBLE);//repeat
                                beenInDay=true;
                            }
                            if(!beenInDay) {
                                Iterator<Date> iter = weeksWithMealplan.iterator();
                                Date dato = weeksWithMealplan.get(0);
                                for (index = 0; iter.hasNext(); index++) {
                                    foundweekDay = dato == selectedDate;
                                    if (foundweekDay) break;
                                    dato = iter.next();
                                    iter.remove();
                                }
                                if (foundweekDay) {
                                    if (selectedDate != WeekDayPassed) {
                                        value = weekPlan.getItems().get(index).getValue();
                                        dayInWeek = 0;
                                        WeekDayPassed = selectedDate;
                                    } else {
                                        dayInWeek++;
                                        value = weekPlan.getItems().get(index + dayInWeek+meal).getValue();
                                    }

                                    jon = new JsonParser().parse(value).getAsJsonObject();
                                    image = jon.get("id").getAsString();
                                    image = image + "-556x370.jpg";
                                    day.setVisibility(View.VISIBLE);
                                    noplan.setVisibility(View.GONE);
                                }
                            }
                            else {
                                noplan.setVisibility(View.VISIBLE);
                                day.setVisibility(View.GONE);
                            }

                            if(image!=null)
                            {
                                String BASE_URL = "https://spoonacular.com/recipeImages/";
                                String imageUrl;

                                if (image.contains("https"))
                                    imageUrl = image;
                                else
                                    imageUrl = BASE_URL + image;

                                Picasso.with(getContext()).load(imageUrl).fit().into(breakfast);
                            }

                            //dinner
                            if(daysWithMealplan.contains(selectedDate)){
                                image =dayPlan.getRecipeModels().get(meal).getImage();
                                noplan.setVisibility(View.GONE);
                                day.setVisibility(View.VISIBLE);
                                beenInDay=true;
                            }
                            if(!beenInDay) {
                                Iterator<Date> iter = weeksWithMealplan.iterator();
                                Date dato = weeksWithMealplan.get(0);
                                for (index = 0; iter.hasNext(); index++) {
                                    foundweekDay = dato == selectedDate;
                                    if (foundweekDay) break;
                                    dato = iter.next();
                                    iter.remove();
                                }
                                if (foundweekDay) {
                                    if (selectedDate != WeekDayPassed) {
                                        value = weekPlan.getItems().get(index).getValue();
                                        dayInWeek = 0;
                                        WeekDayPassed = selectedDate;
                                    } else {
                                        dayInWeek++;
                                        value = weekPlan.getItems().get(index + dayInWeek+meal).getValue();
                                    }

                                    jon = new JsonParser().parse(value).getAsJsonObject();
                                    image = jon.get("id").getAsString();
                                    image = image + "-556x370.jpg";
                                    day.setVisibility(View.VISIBLE);
                                    noplan.setVisibility(View.GONE);
                                }
                            }
                            else {
                                noplan.setVisibility(View.VISIBLE);
                                day.setVisibility(View.GONE);
                            }

                            if(image!=null)
                            {
                                String BASE_URL = "https://spoonacular.com/recipeImages/";
                                String imageUrl;

                                if (image.contains("https"))
                                    imageUrl = image;
                                else
                                    imageUrl = BASE_URL + image;

                                Picasso.with(getContext()).load(imageUrl).fit().into(breakfast);
                            }
                        }
                    };
                    mainHandler.post(myRunnable);


        breakfast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id;
                //id= dayModel.getRecipeModels().get(0).getId();
                    String value =  weekPlan.getItems().get(0).getValue();
                    JsonObject jon = new JsonParser().parse(value).getAsJsonObject();
                    id = jon.get("id").getAsInt();


                final Intent intent = new Intent(MealPlanFragment.this.getActivity().getApplication(), DetailRecipeActivity.class);
                intent.putExtra("id", id);

                startActivity(intent);
            }
        });

        lunch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id;
                //id= dayModel.getRecipeModels().get(1).getId();
                    String value =  weekPlan.getItems().get(1).getValue();
                    JsonObject jon = new JsonParser().parse(value).getAsJsonObject();
                    id = jon.get("id").getAsInt();

                final Intent intent=new Intent(MealPlanFragment.this.getActivity().getApplication(),DetailRecipeActivity.class);
                intent.putExtra("id",id);

                startActivity(intent);

            }
        });

        dinner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id;

                //id= dayModel.getRecipeModels().get(2).getId();
                    String value =  weekPlan.getItems().get(2).getValue();
                    JsonObject jon = new JsonParser().parse(value).getAsJsonObject();
                    id = jon.get("id").getAsInt();


                final Intent intent=new Intent(MealPlanFragment.this.getActivity().getApplication(),DetailRecipeActivity.class);
                intent.putExtra("id",id);

                startActivity(intent);

            }
        });

        compactCalenderView.setListener(new CompactCalendarView.CompactCalendarViewListener() {

            @Override
            public void onDayClick(Date dateClicked) {
                toolbar.setTitle(dateFormatForMonth.format(dateClicked));
                selectedDate=dateClicked;
                myRunnable.run();

            }

            @Override
            public void onMonthScroll(Date firstDayOfNewMonth) {
                toolbar.setTitle(dateFormatForMonth.format(firstDayOfNewMonth));
                selectedDate=firstDayOfNewMonth;
                myRunnable.run();
            }
        });

        showNextMonthBut.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                compactCalenderView.showNextMonth();
            }
        });

        showPreviousMonthBut.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                compactCalenderView.showPreviousMonth();
            }
        });



        // Inflate the layout for this fragment


        return view;
    }



    @Override
    public void getDayPlan(MealPlanDayModel mealplan,List<Date> dates){
            daysWithMealplan.addAll(dates);
            dayPlan=mealplan;
    }

    @Override
    public void getWeekPlan(MealPlanWeekModel mealplan,List<Date> dates){
        weeksWithMealplan.addAll(dates);
        weekPlan=mealplan;

    }

    public void onResume()
    {
        super.onResume();
        Log.e("return","you have returned");
        presenter.update();
    }
}
