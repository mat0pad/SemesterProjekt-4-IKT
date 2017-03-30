package com.sem4ikt.uni.recipefinderchatbot.database;

import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.sem4ikt.uni.recipefinderchatbot.model.spoonacular.MealPlanModel;

public class DatabaseApi implements IDatabaseApi {

  public  boolean SaveUser(String userName, String UID){
      DatabaseReference ref=FirebaseDatabase.getInstance().getReference("user");
      ref.child(UID).setValue(userName);
      return true;
  }

   public boolean SaveRecipe(){
       User user = new User("Anton","mail");
       DatabaseReference mDatabase;

       mDatabase = FirebaseDatabase.getInstance().getReference();
       mDatabase.child("user").child("2").push();

       return true;
    }
    public boolean SaveMealplan(MealPlanModel mealPlan,String UID){
        DatabaseReference ref= FirebaseDatabase.getInstance().getReference();
        ref.child("Mealplan").child(UID).setValue(mealPlan);

        return true;
    }
    public boolean SaveCalories(){
        return true;
    }
    private String userGetVar;
    @Override
    public String getUser(String UID) {
        DatabaseReference ref=FirebaseDatabase.getInstance().getReference("user");

        ref.addListenerForSingleValueEvent(new ValueEventListener(){
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                userGetVar = (String) dataSnapshot.getValue();

            }
                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

        return userGetVar;
    }

    public boolean GetRecipe(){
        return true;
    }
    public boolean GetMealplan(){
        return true;
    }
    public boolean GetCalories(){
        return true;
    }
    public boolean DeleteRecipe(){
        return true;
    }
    public boolean DeleteMealplan(){
        return true;
    }
    public boolean DeleteCalories(){
        return true;
    }
}
