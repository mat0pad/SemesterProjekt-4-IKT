package com.sem4ikt.uni.recipefinderchatbot.database;

import com.google.firebase.auth.FirebaseAuth;

/**
 * Created by anton on 16-03-2017.
 */

public class Authentication implements IFirebaseAuth {

    private FirebaseAuth auth;

    Authentication(){
        auth = FirebaseAuth.getInstance();
    }

    @Override
    public void createUserWithEmailAndPassword(String email, String password) {



    }

    @Override
    public void signIn(String email, String password) {

    }

    @Override
    public void sendEmailAutchenitaction(String email) {

    }
}
