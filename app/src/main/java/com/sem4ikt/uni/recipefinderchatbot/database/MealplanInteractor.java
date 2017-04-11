package com.sem4ikt.uni.recipefinderchatbot.database;

import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.sem4ikt.uni.recipefinderchatbot.database.Interface.ICallbackMealplan;
import com.sem4ikt.uni.recipefinderchatbot.database.Interface.ICallbackRecipe;
import com.sem4ikt.uni.recipefinderchatbot.database.Interface.IFirebaseDBInteractors;
import com.sem4ikt.uni.recipefinderchatbot.model.spoonacular.MealPlanModel;
import com.sem4ikt.uni.recipefinderchatbot.model.spoonacular.RecipeModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by anton on 01-04-2017.
 */

public class MealplanInteractor implements IFirebaseDBInteractors.IMealplanInteractor {

    private DatabaseReference database;

    public MealplanInteractor() {
         database = FirebaseDatabase.getInstance().getReference("mealplan/"+ FirebaseAuth.getInstance().getCurrentUser().getUid());
    }

    @Override
    public void addMealPlan(MealPlanModel mealplan) {
        database.push().setValue(mealplan);
    }

    @Override
    public void removeMealplan() {
        database.removeValue();
    }

    @Override
    public void getMealplan(final ICallbackMealplan callback) {
        database.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.e("GetMealplan","call");
                MealPlanModel mealplan = dataSnapshot.getValue(MealPlanModel.class);

                callback.onReceived(mealplan, ICallbackMealplan.MEALPLAN_CALLBACK_TYPE.GET_MEALPLAN);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e("onCancelled",databaseError.getMessage());
            }
        });
    }
}
