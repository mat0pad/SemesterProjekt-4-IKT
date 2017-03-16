package com.sem4ikt.uni.recipefinderchatbot.Database;

/**
 * Created by anton on 16-03-2017.
 */

public interface IFirebaseAuth {
    void getUser(String email,String password);
    boolean createUser(String email, String password);
}
