package com.sem4ikt.uni.recipefinderchatbot.database;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DatabaseApi implements IDatabaseApi {
   public boolean SaveRecipe(){
       User user = new User("Anton","mail");
       DatabaseReference mDatabase;

       mDatabase = FirebaseDatabase.getInstance().getReference();
       mDatabase.child("user").child("2").setValue(user);

       return true;
    }
    public boolean SaveMealplan(){
        return true;
    }
    public boolean SaveCalories(){
        return true;
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
