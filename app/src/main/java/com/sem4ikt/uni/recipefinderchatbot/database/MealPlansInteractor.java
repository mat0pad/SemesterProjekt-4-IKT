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
import com.sem4ikt.uni.recipefinderchatbot.database.Interface.ICallbackDayMealplan;
import com.sem4ikt.uni.recipefinderchatbot.database.Interface.ICallbackMealPlanAdd;
import com.sem4ikt.uni.recipefinderchatbot.database.Interface.ICallbackWeekMealplan;
import com.sem4ikt.uni.recipefinderchatbot.database.Interface.IFirebaseDBInteractors;
import com.sem4ikt.uni.recipefinderchatbot.model.firebasedb.DateModel;
import com.sem4ikt.uni.recipefinderchatbot.model.firebasedb.DateSetup;
import com.sem4ikt.uni.recipefinderchatbot.model.spoonacular.MealPlanDayModel;
import com.sem4ikt.uni.recipefinderchatbot.model.spoonacular.MealPlanWeekModel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by anton on 01-04-2017.
 */

public class MealPlansInteractor implements IFirebaseDBInteractors.IMealplanInteractor {

    private DatabaseReference mealplanweekdb;
    private DatabaseReference datedb;
    private DatabaseReference mealplandaydb;

    private List<Date> dateweekList;
    private List<Date> datedaylist;

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
        dateweekList = new ArrayList<>();
        datedaylist = new ArrayList<>();


    }

    @Override
    public void addMealPlanWeek(final MealPlanWeekModel mealplan, final Date startdate, ICallbackMealPlanAdd callback) {

        DateSetup ds = new DateSetup();

        final DateModel dateModel = ds.setDateModelWeek(startdate);

        checkForRoom(mealplan,dateModel,callback);
    }



    @Override
    public void addMealPlanDay(MealPlanDayModel mealplan, Date startdate,ICallbackMealPlanAdd callback) {

        DateSetup ds = new DateSetup();

        DateModel dateModel = ds.setDateModelDay(startdate);

        checkForInside(mealplan,dateModel,callback);
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
        DateModel dm = ds.setDateModelWeek(startdate);


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
        DateModel dm = ds.setDateModelWeek(startdate);


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
    public void getMealPlanWeek(final ICallbackWeekMealplan callback) {

        mealplanweekdb.addListenerForSingleValueEvent(new ValueEventListener() {
            final List<MealPlanWeekModel> mealplanlist = new ArrayList<>();


            @Override
            public void onDataChange(DataSnapshot mealplanweeksnapshot) {
                final List<String> keylist = new ArrayList<>();
                for(final DataSnapshot datamealplan: mealplanweeksnapshot.getChildren()) {
                    mealplanlist.add(datamealplan.getValue(MealPlanWeekModel.class));
                    keylist.add(datamealplan.getKey());
                }

                dateweekList.clear();

                for(int i =0;i< mealplanlist.size();++i) {
                    datedb.child(keylist.get(i)).addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot datasnapshot) {
                            if(datasnapshot.exists()) {
                                long b = datasnapshot.getValue(DateModel.class).startDate;
                                dateweekList.add(new Date(b));
                                if (dateweekList.size() == mealplanlist.size())
                                    callback.onReceivedWeek(mealplanlist, dateweekList, ICallbackWeekMealplan.MEALPLAN_WEEK_CALLBACK_TYPE.SUCCCES);
                            }
                            else
                                callback.onReceivedWeek(null,null, ICallbackWeekMealplan.MEALPLAN_WEEK_CALLBACK_TYPE.FAILURE);
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError){
                            callback.onReceivedWeek(null,null, ICallbackWeekMealplan.MEALPLAN_WEEK_CALLBACK_TYPE.FAILURE);
                            Log.e("onCancelled",databaseError.getMessage());
                        }
                    });
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                callback.onReceivedWeek(null,null, ICallbackWeekMealplan.MEALPLAN_WEEK_CALLBACK_TYPE.FAILURE);
                Log.e("onCancelled",databaseError.getMessage());
            }
        });
    }

    @Override
    public void getMealPlanDay(final ICallbackDayMealplan callback) {
        final List<MealPlanDayModel> mealplandaylist = new ArrayList<>();

        mealplandaydb.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot mealplandaysnapshot) {
                final List<String> keylist = new ArrayList<>();
                for (final DataSnapshot datamealplan : mealplandaysnapshot.getChildren()) {
                    mealplandaylist.add(datamealplan.getValue(MealPlanDayModel.class));
                    keylist.add(datamealplan.getKey());
                }

                datedaylist.clear();

                for (int i = 0; i < mealplandaylist.size(); ++i) {
                    datedb.child(keylist.get(i)).addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot datasnapshot) {
                            if (datasnapshot.exists()) {
                                long b = datasnapshot.getValue(DateModel.class).startDate;
                                datedaylist.add(new Date(b));
                                if (datedaylist.size() == mealplandaylist.size())
                                    callback.onReceivedDay(mealplandaylist, datedaylist, ICallbackDayMealplan.MEALPLAN_DAY_CALLBACK_TYPE.SUCCCES);
                            }
                            else
                                callback.onReceivedDay(mealplandaylist, datedaylist, ICallbackDayMealplan.MEALPLAN_DAY_CALLBACK_TYPE.FAILURE);

                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {
                            callback.onReceivedDay(mealplandaylist, datedaylist, ICallbackDayMealplan.MEALPLAN_DAY_CALLBACK_TYPE.FAILURE);
                            Log.e("onCancelled", databaseError.getMessage());
                        }
                    });

                }
            }
                @Override
                public void onCancelled (DatabaseError databaseError){
                    callback.onReceivedDay(mealplandaylist, datedaylist, ICallbackDayMealplan.MEALPLAN_DAY_CALLBACK_TYPE.FAILURE);
                    Log.e("onCancelled",databaseError.getMessage());
                }

        });
    }



    private void checkForRoom(final MealPlanWeekModel mealplan,final DateModel dateModel,final ICallbackMealPlanAdd callback)
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
                                callback.onReceived(ICallbackMealPlanAdd.ADD_CALLBACK_TYPE.SUCCESS);
                                }
                            }


                        @Override
                        public void onCancelled(DatabaseError databaseError) {
                            Log.e("onCancelled",databaseError.getMessage());
                            callback.onReceived(ICallbackMealPlanAdd.ADD_CALLBACK_TYPE.FAILURE);
                        }
                    });
                }
                else
                    callback.onReceived(ICallbackMealPlanAdd.ADD_CALLBACK_TYPE.FAILURE);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e("onCancelled",databaseError.getMessage());
                callback.onReceived(ICallbackMealPlanAdd.ADD_CALLBACK_TYPE.FAILURE);
            }
        });
    }

    private void checkForInside(final MealPlanDayModel mealplanday, final DateModel dateModel,final ICallbackMealPlanAdd callback)
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
                        callback.onReceived(ICallbackMealPlanAdd.ADD_CALLBACK_TYPE.FAILURE);
                        return;
                    }

                }
                insertMealPlanDay(mealplanday,dateModel);
                callback.onReceived(ICallbackMealPlanAdd.ADD_CALLBACK_TYPE.SUCCESS);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e("onCancelled",databaseError.getMessage());
                callback.onReceived(ICallbackMealPlanAdd.ADD_CALLBACK_TYPE.FAILURE);
            }
        });
    }
}
