package com.sem4ikt.uni.recipefinderchatbot.database;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.sem4ikt.uni.recipefinderchatbot.database.Interface.IFirebaseDBInteractors;
import com.sem4ikt.uni.recipefinderchatbot.model.spoonacular.MealPlanModel;

/**
 * Created by anton on 01-04-2017.
 */

public class MealplanInteractor implements IFirebaseDBInteractors.IMealplanInteractor {

    private DatabaseReference mealplandatabase;

    public MealplanInteractor(String uid) {
         mealplandatabase = FirebaseDatabase.getInstance().getReference("mealplan/"+ FirebaseAuth.getInstance().getCurrentUser().getUid());
    }

    @Override
    public void addMealPlan(MealPlanModel mealplan) {

    }

    @Override
    public void removeMealplan(MealPlanModel mealplan) {

    }

    @Override
    public void getMealplan() {
    }
}
