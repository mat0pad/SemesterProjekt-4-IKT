package com.sem4ikt.uni.recipefinderchatbot.database.FirebaseDB;

import android.net.sip.SipAudioCall;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.sem4ikt.uni.recipefinderchatbot.database.FirebaseDB.Interface.IFirebaseDBRepository;
import com.sem4ikt.uni.recipefinderchatbot.database.FirebaseDB.Interface.IFirebaseInteractor;
import com.sem4ikt.uni.recipefinderchatbot.database.FirebaseDB.Interface.IOnGetDataListener;
import com.sem4ikt.uni.recipefinderchatbot.database.FirebaseDB.Model.User;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;

/**
 * Created by anton on 01-04-2017.
 */

public class UserRepository implements IFirebaseDBRepository.IUserRepository {

    private DatabaseReference userDatabase;
    private User user;

    public UserRepository(String uid)
    {
        userDatabase = FirebaseDatabase.getInstance().getReference("User/"+uid);
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
    public User getUser() {

        //final Semaphore semaphore = new Semaphore(0);
        userDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                user = dataSnapshot.getValue(User.class);
               // semaphore.release();
                Log.e("ondatachange",user.username);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
        //semaphore.acquire();
        return user;
    }
}
