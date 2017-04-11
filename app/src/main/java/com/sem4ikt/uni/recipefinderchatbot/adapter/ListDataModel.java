package com.sem4ikt.uni.recipefinderchatbot.adapter;

import android.os.Parcelable;

/**
 * Created by henriknielsen on 06/04/2017.
 */

public interface ListDataModel extends Parcelable {
    enum ListDataType {
        INGREDIENT,
        NUTRIENT,
        RECIPE
    }
}
