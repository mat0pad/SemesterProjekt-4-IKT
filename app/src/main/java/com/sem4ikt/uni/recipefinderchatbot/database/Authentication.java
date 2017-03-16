package com.sem4ikt.uni.recipefinderchatbot.database;

import android.support.annotation.NonNull;
import android.util.Log;

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
    private boolean result;

    public Authentication(LoginActivity loginActivity){

        this.loginActivity = loginActivity;

        auth = FirebaseAuth.getInstance();
    }

    @Override
    public void createUserWithEmailAndPassword(String email, String password) {

        result = false;

        auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(loginActivity, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.i("Authentication", "createUserWithEmailAndPassword: " + task.isSuccessful());

                        if(task.isSuccessful()) {
                            task.getResult().getUser().sendEmailVerification();
                            result = true;
                        }
                        loginActivity.onClearText(result);
                    }
                });


       // return result;

    }

    @Override
    public void signIn(String email, String password) {

        result = false;

        auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(loginActivity, new OnCompleteListener<AuthResult>() {

                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.i("Authentication", "signInWithEmail: " + task.isSuccessful());

                        Log.i("Thread: ", Thread.currentThread().getName());

                        if(task.getResult().getUser().isEmailVerified()){
                            System.out.println("Success");
                            result = true;
                        }
                        else{
                            System.out.println("Email Not Verified!");
                            result = true;
                        }

                        loginActivity.onClearText(result);
                    }
                });
    }

    @Override
    public boolean sendEmailAutchenitaction(String email) {

        //auth.
        return false;
    }
}
