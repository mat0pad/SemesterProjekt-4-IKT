package com.sem4ikt.uni.recipefinderchatbot.fragment;


import android.app.Fragment;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.github.sundeepk.compactcalendarview.CompactCalendarView;
import com.github.sundeepk.compactcalendarview.domain.Event;
import com.sem4ikt.uni.recipefinderchatbot.R;
import com.sem4ikt.uni.recipefinderchatbot.activity.DetailRecipeActivity;
import com.sem4ikt.uni.recipefinderchatbot.model.spoonacular.MealPlanDayModel;
import com.sem4ikt.uni.recipefinderchatbot.model.spoonacular.MealPlanWeekModel;
import com.sem4ikt.uni.recipefinderchatbot.presenter.MealPlanPresenter;
import com.sem4ikt.uni.recipefinderchatbot.presenter.interfaces.IMealPlanPresenter;
import com.sem4ikt.uni.recipefinderchatbot.view.IMealPlanView;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class MealPlanFragment extends Fragment implements IMealPlanView, View.OnClickListener {

    ScrollView day;
    TextView noplan;
    private CompactCalendarView compactCalenderView;
    IMealPlanPresenter presenter;

    List<Event> prikker;
    ActionBar toolbar;

    private SimpleDateFormat dateFormatForMonth;
    private Date selectedDate;

    private ImageView dinnerImage;
    private ImageView breakfastImage;
    private ImageView lunchImage;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        if (container == null)
            return null;

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.mealplan, container, false);

        // Setup presenter
        presenter = new MealPlanPresenter(this);




        // Find views by id
        dinnerImage = (ImageView) view.findViewById(R.id.dinner);
        breakfastImage = (ImageView) view.findViewById(R.id.breakfast);
        lunchImage = (ImageView) view.findViewById(R.id.lunch);
        final TextView month=(TextView) view.findViewById(R.id.month);
        final Button nextButton = (Button) view.findViewById(R.id.next_button);
        final Button preButton = (Button) view.findViewById(R.id.prev_button);

        dinnerImage.setOnClickListener(this);
        lunchImage.setOnClickListener(this);
        breakfastImage.setOnClickListener(this);
        nextButton.setOnClickListener(this);
        preButton.setOnClickListener(this);




        compactCalenderView = (CompactCalendarView) view.findViewById(R.id.compactcalendar_view);

        day = (ScrollView) view.findViewById(R.id.dayview); //visibility GONE if no plan for date
        noplan = (TextView) view.findViewById(R.id.noplan); //visibility VISIBLE if no plan for date

        // Fecth data
        presenter.getMealPlanWeek();
        presenter.getMealPlanDay();

        // Init members

        selectedDate = new Date();
        dateFormatForMonth = new SimpleDateFormat("MMM - yyyy", Locale.getDefault());
        prikker=new ArrayList<>();
        //setup show month
         //toolbar = ((AppCompatActivity) getActivity()).getSupportActionBar();
         //toolbar.setTitle(dateFormatForMonth.format(compactCalenderView.getFirstDayOfCurrentMonth()));
        month.setText(dateFormatForMonth.format(compactCalenderView.getFirstDayOfCurrentMonth()));



        selectedDate = presenter.getTime();

        String[] imageURLs=presenter.loadMealplans(selectedDate);
        if(imageURLs==null){
            presenter.showNoPlan();
        }
        else {
            presenter.showMealplanForDay(imageURLs);
        }


        compactCalenderView.setListener(new CompactCalendarView.CompactCalendarViewListener() {

            @Override
            public void onDayClick(Date dateClicked) {
               // toolbar.setTitle(dateFormatForMonth.format(dateClicked));
                month.setText(dateFormatForMonth.format(dateClicked));

                selectedDate=dateClicked;
                String[] imageURLs=presenter.loadMealplans(selectedDate);
                if(imageURLs==null){
                    presenter.showNoPlan();
                }
                else {
                    presenter.showMealplanForDay(imageURLs);
                }

            }

            @Override
            public void onMonthScroll(Date firstDayOfNewMonth) {
               // toolbar.setTitle(dateFormatForMonth.format(firstDayOfNewMonth));
                month.setText(dateFormatForMonth.format(firstDayOfNewMonth));
                selectedDate=firstDayOfNewMonth;
                String[] imageURLs=presenter.loadMealplans(selectedDate);
                if(imageURLs==null){
                   presenter.showNoPlan();
                }
                else {
                    presenter.showMealplanForDay(imageURLs);
                }
            }
        });

        return view;
    }


    @Override
    public void onNextPressed() {
        compactCalenderView.showNextMonth();
    }

    @Override
    public void onPreviousPressed() {
        compactCalenderView.showPreviousMonth();
    }

    @Override
    public void onShowDetailRecipe(int id) {

       id= presenter.getID(id);

        Intent intent = new Intent(getActivity(), DetailRecipeActivity.class).putExtra("id", id);
        startActivity(intent);
        Log.e("id after intent",Integer.toString(id));
    }

    @Override
    public void getDayPlan(List<MealPlanDayModel> mealplan,List<Date> dates){

        if (mealplan != null && dates != null) {
            presenter.InitDayPlans(mealplan,dates);
            Calendar kal= Calendar.getInstance();
            for (int i=0;i<dates.size();i++){
                kal.setTime(dates.get(i));
                for (int j=0;j<7;j++) {
                    prikker.add(new Event(Color.GREEN, kal.getTimeInMillis(), null));
                    kal.add(Calendar.DATE,1);
                }
            }
        }
    }

    @Override
    public void getWeekPlan(List<MealPlanWeekModel> mealplan,List<Date> dates){

        if (mealplan != null && dates != null) {
           presenter.InitWeekPlans(mealplan,dates);
            Calendar kal= Calendar.getInstance();
            for (int i=0;i<dates.size();i++){
                kal.setTime(dates.get(i));
                for (int j=0;j<7;j++) {
                    prikker.add(new Event(Color.GREEN, kal.getTimeInMillis(), null));
                    kal.add(Calendar.DATE,1);
                }
            }
            compactCalenderView.addEvents(prikker);
        }
    }

    @Override
   public void insertPictures(String[] imageURLs){
        Picasso.with(getActivity()).load(imageURLs[0]).fit().into(breakfastImage);
        Picasso.with(getActivity()).load(imageURLs[1]).fit().into(lunchImage);
        Picasso.with(getActivity()).load(imageURLs[2]).fit().into(dinnerImage);
    }

    @Override
    public void showNoPlan() {
        noplan.setVisibility(View.VISIBLE);
        day.setVisibility(View.GONE);
    }

    @Override
    public void showPlan() {
        noplan.setVisibility(View.GONE);
        day.setVisibility(View.VISIBLE);
    }

    @Override
    public void showErrorToast() {
        Toast.makeText(getView().getContext(),"Error in retriving meal plans, please try againe later",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.dinner:
                presenter.doDinner();

            case R.id.lunch:
                presenter.doLunch();
                break;

            case R.id.breakfast:
                presenter.doBreakfast();
                break;

            case R.id.prev_button:
                presenter.doPrevious();
                break;

            case R.id.next_button:
                presenter.doNext();
                break;

            default:
                break;

        }
    }

}
