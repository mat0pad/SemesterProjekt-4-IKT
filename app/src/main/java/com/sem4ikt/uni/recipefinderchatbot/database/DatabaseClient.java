package com.sem4ikt.uni.recipefinderchatbot.database;

import com.sem4ikt.uni.recipefinderchatbot.database.Interface.IDatabaseClient;

/**
 * Created by kaspe on 30-03-2017.
 */

public class DatabaseClient implements IDatabaseClient {

    public boolean AddToDatabase(){
        return true;
    }
    public boolean GetFromDatabase(){
        return true;
    }
    public boolean DeleteFromDatabase(){
        return true;
    }
}
