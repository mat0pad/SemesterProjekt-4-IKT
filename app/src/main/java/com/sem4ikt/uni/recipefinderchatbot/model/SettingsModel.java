package com.sem4ikt.uni.recipefinderchatbot.model;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.sem4ikt.uni.recipefinderchatbot.model.interfaces.ISettingsModel;
import com.sem4ikt.uni.recipefinderchatbot.presenter.interfaces.ISettingsPresenter;

import static com.google.android.gms.internal.zzt.TAG;


/**
 * Created by Christian on 07-04-2017.
 */

public class SettingsModel implements ISettingsModel {

    //private String newPass;

    String newPassword = "SOME-SECURE-PASSWORD";

    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

    /*@Override
    public boolean checkPasswordsMatches() {
        return confirmNewPass.equals(newPass);
    }

    @Override
    public boolean CheckPasswordValidity(){

        String PASS_REGEX = "((?=.*\\d)(?=.*[a-z]).{6,20})";

        return newPass.matches(PASS_REGEX);
    }*/

    @Override
    public void updatePassword(){

        user.updatePassword(newPassword)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Log.d(TAG, "User password updated.");
                        }
                    }
                });
    }

    @Override
    public void reAuth() {

        // Get auth credentials from the user for re-authentication. The example below shows
        // email and password credentials but there are multiple possible providers,
        // such as GoogleAuthProvider or FacebookAuthProvider.
        AuthCredential credential = EmailAuthProvider
                .getCredential("user@example.com", "password1234");

        // Prompt the user to re-provide their sign-in credentials
        user.reauthenticate(credential)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Log.d(TAG, "User re-authenticated.");
                    }
                });
    }

    @Override
    public void deleteUser(){

        user.delete()
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Log.d(TAG, "User account deleted.");

                        }
                    }
                });
    }
}
