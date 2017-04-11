package com.sem4ikt.uni.recipefinderchatbot.adapter;

import android.os.Parcel;
import android.os.Parcelable;

import com.sem4ikt.uni.recipefinderchatbot.model.interfaces.ListDataModel;
import com.sem4ikt.uni.recipefinderchatbot.model.spoonacular.IngredientsModel;
import com.sem4ikt.uni.recipefinderchatbot.model.spoonacular.NutrientsModel;
import com.sem4ikt.uni.recipefinderchatbot.model.spoonacular.RecipesModel;

import java.util.List;
import java.util.Objects;

/**
 * Created by henriknielsen on 06/04/2017.
 */

public class ListDataContainer implements Parcelable {
    public static final Parcelable.Creator<ListDataContainer> CREATOR
            = new Parcelable.Creator<ListDataContainer>() {

        // This simply calls our new constructor (typically private) and
        // passes along the unmarshalled `Parcel`, and then returns the new object!
        @Override
        public ListDataContainer createFromParcel(Parcel in) {
            return new ListDataContainer(in);
        }

        @Override
        public ListDataContainer[] newArray(int size) {
            return new ListDataContainer[size];
        }
    };
    private String type;
    private List<Object> list;

    public ListDataContainer(List<Object> list, ListDataModel.ListDataType type) {
        this.list = list;
        this.type = type.toString();
    }

    private ListDataContainer(Parcel in) {
        type = in.readString();

        if (Objects.equals(type, ListDataModel.ListDataType.RECIPE.toString()))
            list = in.readArrayList(RecipesModel.class.getClassLoader());
        else if (Objects.equals(type, ListDataModel.ListDataType.INGREDIENT.toString()))
            list = in.readArrayList(IngredientsModel.class.getClassLoader());
        else if (Objects.equals(type, ListDataModel.ListDataType.NUTRIENT.toString()))
            list = in.readArrayList(NutrientsModel.class.getClassLoader());
    }

    public String getType() {
        return type;
    }

    public List<Object> getList() {
        return list;
    }

    // Allows this class to be passed as a parcel -> very fast
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(type);
        parcel.writeList(list);
    }
}
