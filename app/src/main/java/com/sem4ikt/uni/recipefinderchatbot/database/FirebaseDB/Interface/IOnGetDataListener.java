package com.sem4ikt.uni.recipefinderchatbot.database.FirebaseDB.Interface;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;

/**
 * Created by anton on 02-04-2017.
 */

public interface IOnGetDataListener {
    public void onStart();
    public void onSucces(DataSnapshot data);
    public void onFailed(DatabaseError databaseError);
}
