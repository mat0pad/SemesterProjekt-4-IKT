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
import java.util.Date;
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

        dinner = (ImageView) view.findViewById(R.id.dinner);
        breakfast = (ImageView) view.findViewById(R.id.breakfast);
        lunch = (ImageView) view.findViewById(R.id.lunch);
        day= (ScrollView) view.findViewById(R.id.dayview);// set visibility to GONE if no plan for date
        noplan= (TextView) view.findViewById(R.id.noplan);//set visibility to visible if no plan for date

        presenter.getMealPlanWeek();

        /*
        compactCalenderView.setListener(new CompactCalendarView.CompactCalendarViewListener() {

            @Override
            public void onDayClick(Date dateClicked) {
                toolbar.setTitle(dateFormatForMonth.format(dateClicked));

            }

            @Override
            public void onMonthScroll(Date firstDayOfNewMonth) {
                toolbar.setTitle(dateFormatForMonth.format(firstDayOfNewMonth));
            }
        });
        */

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

/*
        ApiClient client = new ApiClient();
        ISpoonacularAPI.ICompute apiService = client.getClient().create(ISpoonacularAPI.ICompute.class);

        Call<MealPlanWeekModel>  call = apiService.getWeekMealPlan(null, 10000, null);


        call.enqueue(new Callback<MealPlanWeekModel>() {
            @Override
            public void onResponse(Call<MealPlanWeekModel> call, Response<MealPlanWeekModel> response) {

                Log.i("TESTLIST", Integer.toString(response.code()));

                if(response.code() == 200) {



                    final MealPlanWeekModel model = response.body();
                    weekPlan= response.body();
                    //dayPlan= response.body();

                    Handler mainHandler = new Handler(Looper.getMainLooper());

                    Runnable myRunnable = new Runnable() {
                        @Override
                        public void run() {
                            // Pass the data
                            String value =  model.getItems().get(0).getValue();
                            JsonObject jon = new JsonParser().parse(value).getAsJsonObject();
                            String image = jon.get("id").getAsString();
                            image= image+"-556x370.jpg";
                           // image =dayModel.getRecipeModels().get(0).getImage();


                            {
                                String BASE_URL = "https://spoonacular.com/recipeImages/";
                                String imageUrl;

                                if (image.contains("https"))
                                    imageUrl = image;
                                else
                                    imageUrl = BASE_URL + image;

                                Picasso.with(getContext()).load(imageUrl).fit().into(breakfast);
                            }
                             value =  model.getItems().get(1).getValue();
                             jon = new JsonParser().parse(value).getAsJsonObject();
                            image = jon.get("id").getAsString();
                            image= image+"-556x370.jpg";
                            // image =dayModel.getRecipeModels().get(1).getImage();

                            {
                                String BASE_URL = "https://spoonacular.com/recipeImages/";
                                String imageUrl;

                                if (image.contains("https"))
                                    imageUrl = image;
                                else
                                    imageUrl = BASE_URL + image;

                                Picasso.with(getContext()).load(imageUrl).fit().into(lunch);
                            }
                            value =  model.getItems().get(2).getValue();
                            jon = new JsonParser().parse(value).getAsJsonObject();
                            image = jon.get("id").getAsString();
                            image= image+"-556x370.jpg";
                            // image =dayModel.getRecipeModels().get(2).getImage();

                            {
                                String BASE_URL = "https://spoonacular.com/recipeImages/";
                                String imageUrl;

                                if (image.contains("https"))
                                    imageUrl = image;
                                else
                                    imageUrl = BASE_URL + image;

                                Picasso.with(getContext()).load(imageUrl).fit().into(dinner);
                            }

                        }
                    };
                    mainHandler.post(myRunnable);

                }
                else{
                    // Something went wrong
                    Log.e("TESTLIST", response.errorBody().toString());
                }

            }

            @Override
            public void onFailure(Call<MealPlanWeekModel> call, Throwable t) {

            }
        });

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
        }); */



        // Inflate the layout for this fragment


        return view;
    }



    @Override
    public void getDayPlan(){
    }

    @Override
    public void getWeekPlan(){
        Date currentDate=new Date();
        
    }

    public void onResume()
    {
        super.onResume();
        Log.e("return","you have returned");
        presenter.update();
    }
}
