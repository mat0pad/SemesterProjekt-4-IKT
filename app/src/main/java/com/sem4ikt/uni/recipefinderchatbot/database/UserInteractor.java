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
            database = FirebaseDatabase.getInstance().getReference("Test"); //Cant save data if not logged in

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
                if(dataSnapshot.exists())
                    callback.onReceived(dataSnapshot.getValue(User.class), ICallbackUser.UserCallbackType.USER_FOUND);
                else
                    callback.onReceived(null, ICallbackUser.UserCallbackType.USER_NOT_FOUND);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                callback.onFailure();
            }
        });
    }

    @Override
    public void updateUser(String name, boolean returning) {
        User user = new User(name,returning);
        database.setValue(user);
    }

}
