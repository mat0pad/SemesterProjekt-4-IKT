package com.sem4ikt.uni.recipefinderchatbot.database.Interface;

import com.sem4ikt.uni.recipefinderchatbot.model.firebasedb.User;

/**
 * Created by anton on 11-04-2017.
 */

public interface ICallbackUser {
    void onReceived(User user,UserCallbackType type);

    void onFailure();

    enum  UserCallbackType{USER_FOUND,USER_NOT_FOUND};
}
