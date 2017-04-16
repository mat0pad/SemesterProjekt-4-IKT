package com.sem4ikt.uni.recipefinderchatbot.database;

import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.sem4ikt.uni.recipefinderchatbot.database.Interface.ICallbackMealplan;
import com.sem4ikt.uni.recipefinderchatbot.database.Interface.IFirebaseDBInteractors;
import com.sem4ikt.uni.recipefinderchatbot.model.firebasedb.DateModel;
import com.sem4ikt.uni.recipefinderchatbot.model.firebasedb.DateSetup;
import com.sem4ikt.uni.recipefinderchatbot.model.spoonacular.MealPlanDayModel;
import com.sem4ikt.uni.recipefinderchatbot.model.spoonacular.MealPlanWeekModel;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * Created by anton on 01-04-2017.
 */

public class MealPlansInteractor implements IFirebaseDBInteractors.IMealplanInteractor {

    private DatabaseReference mealplanweekdb;
    private DatabaseReference datedb;
    private DatabaseReference mealplandaydb;

    private List<Date> DateList;

    public MealPlansInteractor() {

        if(FirebaseAuth.getInstance().getCurrentUser() != null) {
            String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
            mealplanweekdb = FirebaseDatabase.getInstance().getReference("MealplanWeek/" + uid);
            datedb = FirebaseDatabase.getInstance().getReference("MealplanDate/" + uid);
            mealplandaydb = FirebaseDatabase.getInstance().getReference("MealplanDay/" + uid);
        }
        else {
            mealplanweekdb = FirebaseDatabase.getInstance().getReference("Test"); //Cant save data if not logged in
            datedb = FirebaseDatabase.getInstance().getReference("Test");
            mealplandaydb = FirebaseDatabase.getInstance().getReference("Test");
        }
        DateList = new ArrayList<>();


    }

    @Override
    public void addMealPlanWeek(final MealPlanWeekModel mealplan, final Date startdate) {

        DateSetup ds = new DateSetup();

        final DateModel dateModel = ds.SetDateModelWeek(startdate);

        checkForRoom(mealplan,dateModel);
    }



    @Override
    public void addMealPlanDay(MealPlanDayModel mealplan, Date startdate) {

        DateSetup ds = new DateSetup();

        DateModel dateModel = ds.setDateModelDay(startdate);

        checkForInside(mealplan,dateModel);
    }


    private void insertMealPlanWeek(MealPlanWeekModel mealplan,DateModel dateModel)
    {
        String key = mealplanweekdb.push().getKey(); //get key in advance
        Log.e("KEY",key);
        mealplanweekdb.child(key).setValue(mealplan);
        datedb.child(key).setValue(dateModel);
    }

    private void insertMealPlanDay(MealPlanDayModel mealPlanDayModel, DateModel dateModel)
    {
        String key = mealplandaydb.push().getKey(); //get key in advance
        Log.e("KEY",key);
        mealplandaydb.child(key).setValue(mealPlanDayModel);
        datedb.child(key).setValue(dateModel);
    }




    @Override
    public void removeMealPlanDay(Date startdate){
        DateSetup ds = new DateSetup();
        DateModel dm = ds.SetDateModelWeek(startdate);


        Query dateQuery = datedb.orderByChild("startDate").equalTo(dm.startDate);
        dateQuery.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot datesnapshot : dataSnapshot.getChildren()){
                    String key = datesnapshot.getKey();
                    mealplandaydb.child(key).removeValue();
                    datedb.child(key).removeValue();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e("onCancelled",databaseError.toString());
            }
        });

    }

    @Override
    public void removeMealPlanWeek(Date startdate) {

        DateSetup ds = new DateSetup();
        DateModel dm = ds.SetDateModelWeek(startdate);


        Query dateQuery = datedb.orderByChild("startDate").equalTo(dm.startDate);


        dateQuery.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot datesnapshot : dataSnapshot.getChildren()){
                    String key = datesnapshot.getKey();
                    mealplanweekdb.child(key).removeValue();
                    datedb.child(key).removeValue();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e("onCancelled",databaseError.toString());
            }
        });
    }

    @Override
    public void getMealPlanWeek(final ICallbackMealplan callback) {
        mealplanweekdb.addListenerForSingleValueEvent(new ValueEventListener() {
            final List<MealPlanWeekModel> mealplanlist = new ArrayList<>();

            @Override
            public void onDataChange(DataSnapshot mealplanweeksnapshot) {
                final List<String> keylist = new ArrayList<String>();
                for(final DataSnapshot datamealplan: mealplanweeksnapshot.getChildren()) {
                    mealplanlist.add(datamealplan.getValue(MealPlanWeekModel.class));
                    keylist.add(datamealplan.getKey());
                }

                for(int i =0;i< mealplanlist.size();++i) {
                    datedb.child(keylist.get(i)).addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot datasnapshot) {
                            if(datasnapshot.exists()) {
                                long b = datasnapshot.getValue(DateModel.class).startDate;
                                DateList.add(new Date(b));
                                Log.e("asdkslkj", mealplanlist.size() + "");
                                if (DateList.size() == mealplanlist.size())
                                    callback.onReceived(mealplanlist, DateList, ICallbackMealplan.MEALPLAN_CALLBACK_TYPE.GET_MEALPLAN_WEEK);
                            }
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError){
                            Log.e("onCancelled",databaseError.getMessage());
                        }
                    });
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e("onCancelled",databaseError.getMessage());
            }
        });
    }

    @Override
    public void getMealPlanDay(ICallbackMealplan callback) {

    }


    //isweek = true mealplanweek, isweek = false mealplanday
    private void checkForRoom(final MealPlanWeekModel mealplan,final DateModel dateModel)
    {
        Query upperbound = datedb.orderByChild("endDate").startAt((dateModel.startDate)).endAt(dateModel.endDate);

        upperbound.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(!dataSnapshot.exists()) {
                    Query lowerbound = datedb.orderByChild("startDate").startAt(dateModel.startDate).endAt(dateModel.endDate);

                    lowerbound.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            if (!dataSnapshot.exists()) {
                                insertMealPlanWeek(mealplan, dateModel);

                                }
                            }


                        @Override
                        public void onCancelled(DatabaseError databaseError) {
                            Log.e("onCancelled",databaseError.getMessage());
                        }
                    });
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e("onCancelled",databaseError.getMessage());
            }
        });
    }

    private void checkForInside(final MealPlanDayModel mealplanday, final DateModel dateModel)
    {
        Query lower = datedb.orderByChild("endDate").startAt(dateModel.startDate);

        lower.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(final DataSnapshot datamealplanDay: dataSnapshot.getChildren())
                {
                    DateModel startdateinside = datamealplanDay.getValue(DateModel.class);

                    if(startdateinside.startDate <= dateModel.startDate) {
                        Log.e("tag","tag");
                        return;
                    }

                }
                insertMealPlanDay(mealplanday,dateModel);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
