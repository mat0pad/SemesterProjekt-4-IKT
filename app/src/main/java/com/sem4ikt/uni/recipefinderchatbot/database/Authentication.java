package com.sem4ikt.uni.recipefinderchatbot.database;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.sem4ikt.uni.recipefinderchatbot.database.Interface.IFirebaseAuth;
import com.sem4ikt.uni.recipefinderchatbot.presenter.interfaces.ILoginCallback;

import static com.sem4ikt.uni.recipefinderchatbot.presenter.LoginPresenter.AUTH.CREATE_FAILED;
import static com.sem4ikt.uni.recipefinderchatbot.presenter.LoginPresenter.AUTH.CREATE_SUCCESS;
import static com.sem4ikt.uni.recipefinderchatbot.presenter.LoginPresenter.AUTH.DELETE_ACCOUNT_FAILED;
import static com.sem4ikt.uni.recipefinderchatbot.presenter.LoginPresenter.AUTH.DELETE_ACCOUNT_SUCCESS;
import static com.sem4ikt.uni.recipefinderchatbot.presenter.LoginPresenter.AUTH.FORGOT_PASSWORD_FAILED;
import static com.sem4ikt.uni.recipefinderchatbot.presenter.LoginPresenter.AUTH.FORGOT_PASSWORD_SUCCESS;
import static com.sem4ikt.uni.recipefinderchatbot.presenter.LoginPresenter.AUTH.SIGN_IN_FAILED;
import static com.sem4ikt.uni.recipefinderchatbot.presenter.LoginPresenter.AUTH.SIGN_IN_SUCCESS;
import static com.sem4ikt.uni.recipefinderchatbot.presenter.LoginPresenter.AUTH.UPDATE_PASSWORD_FAILED;
import static com.sem4ikt.uni.recipefinderchatbot.presenter.LoginPresenter.AUTH.UPDATE_PASSWORD_SUCCESS;


/**
 * Created by anton on 16-03-2017.
 */

public class Authentication implements IFirebaseAuth {

    private FirebaseAuth auth;

    public Authentication(){ auth = FirebaseAuth.getInstance(); }

    @Override
    public void createUserWithEmailAndPassword(String email, String password, final ILoginCallback callback) {

        auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if(task.isSuccessful()) {
                            task.getResult().getUser().sendEmailVerification();
                            callback.onAuthenticationFinished(CREATE_SUCCESS, "Created user successfully! Now verify email");
                        }
                        else
                            callback.onAuthenticationFinished(CREATE_FAILED, "Create user failed!");
                    }
                });
    }

    @Override
    public void signIn(String email, String password, final ILoginCallback callback) {

        auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {

                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()) {
                            Log.e("Test",task.getResult().toString());
                            if (task.getResult().getUser().isEmailVerified())
                                callback.onAuthenticationFinished(SIGN_IN_SUCCESS, "Sign in successful!");

                            else
                                callback.onAuthenticationFinished(SIGN_IN_FAILED, "Email not verified!");
                        }
                        else{
                            Log.e("Failure","failed");
                            callback.onAuthenticationFinished(SIGN_IN_FAILED, "User does not exist yet!");
                        }

                    }
                });
    }

    @Override
    public void sendResetEmailVerification(String email, final ILoginCallback callback) {

       auth.sendPasswordResetEmail(email)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                        if(task.isSuccessful()) {
                            callback.onAuthenticationFinished(FORGOT_PASSWORD_SUCCESS, "Email sent. Forgot password successful!");
                        }
                        else
                            callback.onAuthenticationFinished(FORGOT_PASSWORD_FAILED, "Forgot password failed!");
                    }
                });

    }

    @Override
    public void updatePassword(String newPassword, final ILoginCallback callback) {

        if (auth.getCurrentUser() != null) {
            auth.getCurrentUser().updatePassword(newPassword).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {

                    if (task.isSuccessful()) {
                        callback.onAuthenticationFinished(UPDATE_PASSWORD_SUCCESS, "Your password has been updated!");
                    } else
                        callback.onAuthenticationFinished(UPDATE_PASSWORD_FAILED, "Updating your password failed!");
                }
            });
        } else {
            callback.onAuthenticationFinished(UPDATE_PASSWORD_FAILED, "Updating your password failed!");
        }

    }

    @Override
    public void deleteAccount(final ILoginCallback callback) {

        if (auth.getCurrentUser() != null) {
            auth.getCurrentUser().delete().addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {

                    if (task.isSuccessful()) {
                        callback.onAuthenticationFinished(DELETE_ACCOUNT_SUCCESS, "Your account has been deleted!");
                    } else
                        callback.onAuthenticationFinished(DELETE_ACCOUNT_FAILED, "Deleting your account failed!");
                }
            });
        } else {
            callback.onAuthenticationFinished(DELETE_ACCOUNT_FAILED, "Deleting your account failed!");
        }


    }

}
