package com.sem4ikt.uni.recipefinderchatbot.database.Interface;

import com.sem4ikt.uni.recipefinderchatbot.presenter.interfaces.ILoginCallback;

/**
 * Created by anton on 16-03-2017.
 */

public interface IFirebaseAuth {

    void createUserWithEmailAndPassword(String email, String password, ILoginCallback callable);
    void signIn(String email, String password, ILoginCallback callable);
    void sendRestEmailVerification(String email, ILoginCallback callable);

}
