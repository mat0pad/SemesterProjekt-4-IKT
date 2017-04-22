package com.sem4ikt.uni.recipefinderchatbot.database.Interface;

/**
 * Created by anton on 21-04-2017.
 */

public interface ICallbackMealPlanAdd {

    void onReceived(ADD_CALLBACK_TYPE type);

    enum ADD_CALLBACK_TYPE{SUCCESS,FAILURE};
}
