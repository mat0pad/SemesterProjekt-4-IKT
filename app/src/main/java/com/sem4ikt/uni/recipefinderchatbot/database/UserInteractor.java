package com.sem4ikt.uni.recipefinderchatbot.database;

import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.sem4ikt.uni.recipefinderchatbot.database.Interface.ICallbackUser;
import com.sem4ikt.uni.recipefinderchatbot.database.Interface.IFirebaseDBInteractors;
import com.sem4ikt.uni.recipefinderchatbot.model.firebasedb.User;


/**
 * Created by anton on 01-04-2017.
 */

public class UserInteractor implements IFirebaseDBInteractors.IUserInteractor {

    private DatabaseReference database;

    public UserInteractor()
    {
        if(FirebaseAuth.getInstance().getCurrentUser() != null)
            database = FirebaseDatabase.getInstance().getReference("User/"+ FirebaseAuth.getInstance().getCurrentUser().getUid());
        else
            database = FirebaseDatabase.getInstance().getReference("Test");
    }

    @Override
    public void addUser(User user) {
        database.setValue(user);
    }

    @Override
    public void removeUser() {
        database.removeValue();
    }


    @Override
    public void getUser(final ICallbackUser callback)  {

        Log.e("Lock","Starting getUser");
        database.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                callback.onReceived(dataSnapshot.getValue(User.class), ICallbackUser.USER_CALLBACK_TYPE.USER_FOUND);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e("Userinteractor","NONEUSER");
                callback.onReceived(null, ICallbackUser.USER_CALLBACK_TYPE.USER_NOT_FOUND);
            }
        });
    }

}
