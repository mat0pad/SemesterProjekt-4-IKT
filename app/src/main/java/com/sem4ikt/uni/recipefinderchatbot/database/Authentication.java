package com.sem4ikt.uni.recipefinderchatbot.database;

import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.sem4ikt.uni.recipefinderchatbot.LoginActivity;

import static com.sem4ikt.uni.recipefinderchatbot.LoginActivity.AUTH.CREATE_FAILED;
import static com.sem4ikt.uni.recipefinderchatbot.LoginActivity.AUTH.CREATE_SUCCESS;
import static com.sem4ikt.uni.recipefinderchatbot.LoginActivity.AUTH.SIGN_IN_FAILED;
import static com.sem4ikt.uni.recipefinderchatbot.LoginActivity.AUTH.SIGN_IN_SUCCESS;


/**
 * Created by anton on 16-03-2017.
 */

public class Authentication implements IFirebaseAuth {

    private FirebaseAuth auth;
    private LoginActivity loginActivity;

    public Authentication(LoginActivity loginActivity){

        this.loginActivity = loginActivity;

        auth = FirebaseAuth.getInstance();
    }

    @Override
    public void createUserWithEmailAndPassword(String email, String password) {

        auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(loginActivity, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if(task.isSuccessful()) {
                            task.getResult().getUser().sendEmailVerification();
                            loginActivity.authenticationHandler(CREATE_SUCCESS, "Create user successfully! Now verify email");
                    }
                        else
                            loginActivity.authenticationHandler(CREATE_FAILED, "Create user failed!");
                    }
                });
    }

    @Override
    public void signIn(String email, String password) {

        auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(loginActivity, new OnCompleteListener<AuthResult>() {

                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()) {
                            if (task.getResult().getUser().isEmailVerified())
                                loginActivity.authenticationHandler(SIGN_IN_SUCCESS, "Sign in successful!");

                            else
                                loginActivity.authenticationHandler(SIGN_IN_FAILED, "Email not verified!");
                        }
                        else{
                            loginActivity.authenticationHandler(SIGN_IN_FAILED, "User does not exist yet!");
                        }

                    }
                });
    }

    @Override
    public void sendRestEmailVerification(String email) {

        auth.sendPasswordResetEmail(email)
                .addOnCompleteListener(loginActivity, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                        System.out.println(task.isSuccessful());
                    }
                });

    }
}
