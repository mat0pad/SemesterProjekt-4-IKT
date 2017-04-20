package com.sem4ikt.uni.recipefinderchatbot.database;

import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.sem4ikt.uni.recipefinderchatbot.database.Interface.IFirebaseDBInteractors;

/**
 * Created by anton on 18-04-2017.
 */

public class DeleteInfoInteractor implements IFirebaseDBInteractors.IDeleteInfoInteractor {

    @Override
    public void removeAllUserInfo(String uid) {
        final DatabaseReference database = FirebaseDatabase.getInstance().getReference();


        String Uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        database.child("User/"+Uid).removeValue();
        database.child("MealplanDate/"+Uid).removeValue();
        database.child("MealplanWeek/"+Uid).removeValue();
        database.child("Recipe/"+Uid).removeValue();
    }
}
