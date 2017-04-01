package com.sem4ikt.uni.recipefinderchatbot.database.FirebaseDB;

import com.google.firebase.database.IgnoreExtraProperties;

/**
 * Created by kaspe on 30-03-2017.
 */


//TEST
    @IgnoreExtraProperties
public class User {

    public String username;
    public boolean returninguser;

    public User() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public User(String username, boolean returninguser) {
        this.username = username;
        this.returninguser = returninguser;
    }


}
