package com.sem4ikt.uni.recipefinderchatbot.database;

import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.sem4ikt.uni.recipefinderchatbot.LoginActivity;

/**
 * Created by anton on 16-03-2017.
 */

public class Authentication implements IFirebaseAuth {

    private FirebaseAuth auth;
    private LoginActivity loginActivity;

    Authentication(LoginActivity loginActivity){

        this.loginActivity = loginActivity;

        auth = FirebaseAuth.getInstance();
    }

    @Override
    public void createUserWithEmailAndPassword(String email, String password) {

        auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(loginActivity, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {


                    }
                });

    }

    @Override
    public void signIn(String email, String password) {

    }

    @Override
    public void sendEmailAutchenitaction(String email) {

    }
}
