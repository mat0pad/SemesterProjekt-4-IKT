package com.sem4ikt.uni.recipefinderchatbot.database;

/**
 * Created by anton on 16-03-2017.
 */

public interface IFirebaseAuth {

    void createUserWithEmailAndPassword(String email, String password);
    void signIn(String email,String password);
    void sendEmailAutchenitaction(String email);

}
