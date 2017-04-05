package com.sem4ikt.uni.recipefinderchatbot.database;

import android.util.Log;


import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.sem4ikt.uni.recipefinderchatbot.database.Interface.IFirebaseDBInteractors;
import com.sem4ikt.uni.recipefinderchatbot.model.firebasedb.User;
import com.sem4ikt.uni.recipefinderchatbot.presenter.interfaces.IChatbotPresenter;


/**
 * Created by anton on 01-04-2017.
 */

public class UserInteractor implements IFirebaseDBInteractors.IUserInteractor {

    private DatabaseReference userDatabase;

    private IChatbotPresenter callback;


    public UserInteractor(IChatbotPresenter callback)
    {

        userDatabase = FirebaseDatabase.getInstance().getReference("User/"+FirebaseAuth.getInstance().getCurrentUser().getUid());
        this.callback = callback;
    }

    @Override
    public void addUser(User user) {
        userDatabase.setValue(user);
    }

    @Override
    public void removeUser() {
        userDatabase.removeValue();
    }


    @Override
    public void getUser()  {

        Log.e("Lock","Starting getUser");
        userDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                callback.onReceived(dataSnapshot.getValue(User.class));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }

}
