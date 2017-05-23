package com.sem4ikt.uni.recipefinderchatbot.database.Interface;

import com.sem4ikt.uni.recipefinderchatbot.presenter.interfaces.ILoginCallback;

/**
 * Created by anton on 16-03-2017.
 */

public interface IFirebaseAuth {

    void createUserWithEmailAndPassword(String email, String password, ILoginCallback callable);
    void signIn(String email, String password, ILoginCallback callable);
    void sendResetEmailVerification(String email, ILoginCallback callable);
    void updatePassword(String newPassword, final ILoginCallback callback);
    void deleteAccount(final ILoginCallback callback);

}
