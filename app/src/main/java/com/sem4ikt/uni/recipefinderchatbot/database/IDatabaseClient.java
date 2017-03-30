package com.sem4ikt.uni.recipefinderchatbot.database;

/**
 * Created by kaspe on 30-03-2017.
 */

public interface IDatabaseClient {
    boolean AddToDatabase();
    boolean GetFromDatabase();
    boolean DeleteFromDatabase();
}
