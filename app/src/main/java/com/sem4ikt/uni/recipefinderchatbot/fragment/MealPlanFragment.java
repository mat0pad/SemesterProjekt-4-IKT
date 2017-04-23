package com.sem4ikt.uni.recipefinderchatbot.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.app.Fragment;
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
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MealPlanFragment extends Fragment implements IMealPlanView {

    ImageView dinner;
    ImageView breakfast;
    ImageView lunch;
    ScrollView day;
    TextView noplan;
    List<MealPlanWeekModel> weekPlans;
    List<MealPlanDayModel> dayPlans;
    MealPlanPresenter presenter;
    private CompactCalendarView compactCalenderView;
    //private ActionBar toolbar;
    private SimpleDateFormat dateFormatForMonth = new SimpleDateFormat("MMM - yyyy", Locale.GERMANY);
    private Date selectedDate;
    List<Date> daysWithMealplan;
    List<Date> weeksWithMealplan;
    Calendar cal;
    int dayInWeek=0;
    boolean dayplanActive;
    int planIndex;


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


        //toolbar.setTitle(dateFormatForMonth.format(compactCalenderView.getFirstDayOfCurrentMonth()));
        selectedDate=new Date();


        dinner = (ImageView) view.findViewById(R.id.dinner);
        compactCalenderView= (CompactCalendarView) view.findViewById(R.id.compactcalendar_view);
        breakfast = (ImageView) view.findViewById(R.id.breakfast);
        lunch = (ImageView) view.findViewById(R.id.lunch);
        day= (ScrollView) view.findViewById(R.id.dayview);//visibility GONE if no plan for date
        noplan= (TextView) view.findViewById(R.id.noplan);//visibility VISIBLE if no plan for date
        presenter.getMealPlanWeek();
        presenter.getMealPlanDay();
        cal= Calendar.getInstance(Locale.GERMANY);
        daysWithMealplan= new ArrayList<>();
        weeksWithMealplan= new ArrayList<>();
        weekPlans=new ArrayList<>();
        dayPlans=new ArrayList<>();
        cal.set(Calendar.HOUR_OF_DAY,12);
        cal.set(Calendar.MINUTE,0);
        cal.set(Calendar.SECOND,0);
        selectedDate=cal.getTime();



                    final Handler mainHandler = new Handler(Looper.getMainLooper());

                    final Runnable myRunnable = new Runnable() {
                        @Override
                        public void run() {
                            // Pass the data
                            String image = null;
                            String value;
                            JsonObject jon;
                            boolean beenInDay = false;
                            boolean foundweekDay = false;
                            boolean containsPlan = false;
                            cal.setTime(selectedDate);
                            cal.set(Calendar.HOUR_OF_DAY,12);
                            cal.set(Calendar.MINUTE,0);
                            cal.set(Calendar.SECOND,0);
                            selectedDate=cal.getTime();
                            Date mealPlanStart = selectedDate;
                            int dateIndex;
                            int meal = 0;
                            int days;

                            Log.e("DatoTid", ""+selectedDate);
                            //breakfast
                            if(daysWithMealplan!=null) {
                                if (daysWithMealplan.contains(selectedDate)) {
                                    planIndex = daysWithMealplan.indexOf(selectedDate);
                                    image = dayPlans.get(planIndex).getRecipeModels().get(meal).getImage();//if dayplan
                                    noplan.setVisibility(View.GONE);
                                    day.setVisibility(View.VISIBLE);
                                    beenInDay = true;
                                    Log.e("meal day breakfast",Integer.toString(meal));
                                    meal++;

                                }
                            }
                            if (!beenInDay) {
                                if (weeksWithMealplan != null) {
                                    for (dayInWeek = 0; dayInWeek < 7; dayInWeek++) {
                                        cal.add(Calendar.DATE, -dayInWeek);
                                        mealPlanStart = cal.getTime();
                                        containsPlan = weeksWithMealplan.contains(mealPlanStart);//find out if contains plan and find day in week
                                        if (containsPlan) {
                                            foundweekDay = true;
                                            break;
                                        }
                                        cal.add(Calendar.DATE,dayInWeek);
                                    }
                                }
                            }
                                if (containsPlan) {
                                    planIndex = weeksWithMealplan.indexOf(mealPlanStart);
                                    value = weekPlans.get(planIndex).getItems().get(dayInWeek + meal).getValue();
                                    jon = new JsonParser().parse(value).getAsJsonObject();
                                    image = jon.get("id").getAsString();
                                    image = image + "-556x370.jpg";                             //create picture URL
                                    day.setVisibility(View.VISIBLE);
                                    noplan.setVisibility(View.GONE);
                                    Log.e("meal week breakfast",Integer.toString(meal));
                                    meal++;
                                    Log.e("dayInWeek breakfast",Integer.toString(dayInWeek));
                                }

                            else {
                                noplan.setVisibility(View.VISIBLE);
                                day.setVisibility(View.GONE);                   //if no plan exists for day
                            }

                            if(image!=null)
                            {
                                String BASE_URL = "https://spoonacular.com/recipeImages/";
                                String imageUrl;

                                if (image.contains("https"))
                                    imageUrl = image;
                                else
                                    imageUrl = BASE_URL + image;//insert picture

                                Picasso.with(getActivity()).load(imageUrl).fit().into(breakfast);
                                Log.e("url Breakfasr",imageUrl);
                            }

                            //lunch
                            if (beenInDay) {
                                planIndex = daysWithMealplan.indexOf(selectedDate);
                                image = dayPlans.get(planIndex).getRecipeModels().get(meal).getImage();//if dayplan
                                noplan.setVisibility(View.GONE);
                                day.setVisibility(View.VISIBLE);
                                meal++;
                                Log.e("meal day lunch",Integer.toString(meal));
                            }
                            else if (containsPlan) {
                                planIndex = weeksWithMealplan.indexOf(mealPlanStart);
                                value = weekPlans.get(planIndex).getItems().get(dayInWeek + meal).getValue();
                                jon = new JsonParser().parse(value).getAsJsonObject();
                                image = jon.get("id").getAsString();
                                image = image + "-556x370.jpg";                             //create picture URL
                                day.setVisibility(View.VISIBLE);
                                noplan.setVisibility(View.GONE);
                                Log.e("meal week lunch",Integer.toString(meal));
                                meal++;
                                Log.e("dayInWeek lunch",Integer.toString(dayInWeek));
                            }

                            else {
                                image=null;
                                noplan.setVisibility(View.VISIBLE);
                                day.setVisibility(View.GONE);                   //if no plan exists for day
                            }

                            if(image!=null)
                            {
                                String BASE_URL = "https://spoonacular.com/recipeImages/";
                                String imageUrl;

                                if (image.contains("https"))
                                    imageUrl = image;
                                else
                                    imageUrl = BASE_URL + image;//insert picture

                                Picasso.with(getActivity()).load(imageUrl).fit().into(lunch);
                                Log.e("url lunch",imageUrl);
                            }

                            //dinner
                            if (beenInDay) {
                                planIndex = daysWithMealplan.indexOf(selectedDate);
                                image = dayPlans.get(planIndex).getRecipeModels().get(meal).getImage();//if dayplan
                                noplan.setVisibility(View.GONE);
                                day.setVisibility(View.VISIBLE);
                                Log.e("meal day dinner",Integer.toString(meal));
                            }
                            else if (containsPlan) {
                                planIndex = weeksWithMealplan.indexOf(mealPlanStart);
                                value = weekPlans.get(planIndex).getItems().get(dayInWeek + meal).getValue();
                                jon = new JsonParser().parse(value).getAsJsonObject();
                                image = jon.get("id").getAsString();
                                image = image + "-556x370.jpg";                             //create picture URL

                                day.setVisibility(View.VISIBLE);
                                noplan.setVisibility(View.GONE);
                                Log.e("meal week dinner",Integer.toString(meal));
                                Log.e("dayInWeek dinner",Integer.toString(dayInWeek));
                            }

                            else {
                                image=null;
                                noplan.setVisibility(View.VISIBLE);
                                day.setVisibility(View.GONE);                   //if no plan exists for day
                            }

                            if(image!=null)
                            {
                                String BASE_URL = "https://spoonacular.com/recipeImages/";
                                String imageUrl;

                                if (image.contains("https"))
                                    imageUrl = image;
                                else
                                    imageUrl = BASE_URL + image;//insert picture

                                Picasso.with(getActivity()).load(imageUrl).fit().into(dinner);
                                Log.e("url dinner",imageUrl);
                            }
                            dayplanActive=beenInDay;
                        }
                    };
                    mainHandler.post(myRunnable);


        breakfast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id;
                if(dayplanActive){
                    id= dayPlans.get(planIndex).getRecipeModels().get(0).getId();
                }
                else {
                    String value = weekPlans.get(planIndex).getItems().get(dayInWeek+0).getValue();
                    JsonObject jon = new JsonParser().parse(value).getAsJsonObject();
                    id = jon.get("id").getAsInt();
                }

                final Intent intent = new Intent(MealPlanFragment.this.getActivity().getApplication(), DetailRecipeActivity.class);
                intent.putExtra("id", id);

                startActivity(intent);
            }
        });

        lunch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id;
                if(dayplanActive) {
                    id = dayPlans.get(planIndex).getRecipeModels().get(1).getId();
                }
                else {
                    String value = weekPlans.get(planIndex).getItems().get(dayInWeek+1).getValue();
                    JsonObject jon = new JsonParser().parse(value).getAsJsonObject();
                    id = jon.get("id").getAsInt();
                }

                final Intent intent=new Intent(MealPlanFragment.this.getActivity().getApplication(),DetailRecipeActivity.class);
                intent.putExtra("id",id);

                startActivity(intent);

            }
        });

        dinner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id;
                if(dayplanActive) {
                    id= dayPlans.get(planIndex).getRecipeModels().get(2).getId();
                }
                else {
                    String value = weekPlans.get(planIndex).getItems().get(dayInWeek+2).getValue();
                    JsonObject jon = new JsonParser().parse(value).getAsJsonObject();
                    id = jon.get("id").getAsInt();
                }

                final Intent intent=new Intent(MealPlanFragment.this.getActivity().getApplication(),DetailRecipeActivity.class);
                intent.putExtra("id",id);

                startActivity(intent);

            }
        });

        compactCalenderView.setListener(new CompactCalendarView.CompactCalendarViewListener() {

            @Override
            public void onDayClick(Date dateClicked) {
                //toolbar.setTitle(dateFormatForMonth.format(dateClicked));
                selectedDate=dateClicked;
                myRunnable.run();

            }

            @Override
            public void onMonthScroll(Date firstDayOfNewMonth) {
               // toolbar.setTitle(dateFormatForMonth.format(firstDayOfNewMonth));
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
    public void getDayPlan(List<MealPlanDayModel> mealplan,List<Date> dates){
        if(mealplan!=null&&dates!=null) {
            daysWithMealplan.addAll(dates);
            dayPlans.addAll(mealplan);
            Log.e("DatoTid", ""+daysWithMealplan.get(0));
        }
    }

    @Override
    public void getWeekPlan(List<MealPlanWeekModel> mealplan,List<Date> dates){
        if(mealplan!=null && dates!=null) {
            weeksWithMealplan.addAll(dates);
            weekPlans.addAll(mealplan);
            Log.e("DatoTid", ""+weeksWithMealplan.get(0));
        }
    }

    public void onResume()
    {
        super.onResume();
        Log.e("return","you have returned");
        presenter.update();
    }
}
