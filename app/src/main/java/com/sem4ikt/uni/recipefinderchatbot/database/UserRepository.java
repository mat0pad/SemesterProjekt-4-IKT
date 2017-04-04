package com.sem4ikt.uni.recipefinderchatbot.database;

import android.util.Log;

import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.sem4ikt.uni.recipefinderchatbot.database.Interface.IFirebaseDBRepository;
import com.sem4ikt.uni.recipefinderchatbot.model.FirebaseInteractor;
import com.sem4ikt.uni.recipefinderchatbot.model.firebasedb.User;
import com.sem4ikt.uni.recipefinderchatbot.model.interfaces.IFirebaseInteractor;

import java.io.IOException;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by anton on 01-04-2017.
 */

public class UserRepository implements IFirebaseDBRepository.IUserRepository {

    private DatabaseReference userDatabase;

    private IFirebaseInteractor interactor;

    public UserRepository(String uid,IFirebaseInteractor interactor)
    {
        userDatabase = FirebaseDatabase.getInstance().getReference("User/"+uid);
        this.interactor = interactor;
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
                User user = dataSnapshot.getValue(User.class);
                interactor.callback(user);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }

}
