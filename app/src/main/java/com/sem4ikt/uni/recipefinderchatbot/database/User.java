package com.sem4ikt.uni.recipefinderchatbot.database;

/**
 * Created by kaspe on 30-03-2017.
 */


//TEST
public class User {

    public String username;
    public String email;

    public User() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public User(String username, String email) {
        this.username = username;
        this.email = email;
    }

}
