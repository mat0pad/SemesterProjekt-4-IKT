package com.sem4ikt.uni.recipefinderchatbot.database;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.sem4ikt.uni.recipefinderchatbot.database.Interface.IFirebaseDBInteractors;

/**
 * Created by anton on 18-04-2017.
 */

public class DeleteInfoInteractor implements IFirebaseDBInteractors.IDeleteInfoInteractor {

    private DatabaseReference database =  FirebaseDatabase.getInstance().getReference();
    private String uId;

    public DeleteInfoInteractor()
    {
        database =  FirebaseDatabase.getInstance().getReference();
        uId = FirebaseAuth.getInstance().getCurrentUser().getUid();
    }

    @Override
    public void removeAllUserInfo() {
        database.child("User/"+ uId).removeValue();
        database.child("MealplanDate/"+ uId).removeValue();
        database.child("MealplanWeek/"+ uId).removeValue();
        database.child("MealplanDay/"+ uId).removeValue();
        database.child("Recipe/"+ uId).removeValue();
    }
}
