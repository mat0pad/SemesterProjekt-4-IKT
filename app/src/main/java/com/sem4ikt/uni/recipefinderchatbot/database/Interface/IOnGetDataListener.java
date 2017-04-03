package com.sem4ikt.uni.recipefinderchatbot.database.Interface;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;

/**
 * Created by anton on 02-04-2017.
 */

public interface IOnGetDataListener {
    void onStart();

    void onSucces(DataSnapshot data);

    void onFailed(DatabaseError databaseError);
}
