package com.sem4ikt.uni.recipefinderchatbot.model.spoonacular;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


/**
 * Created by mathiaslykkepedersen on 06/03/2017.
 */

public class IngredientsModel implements Parcelable {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("usedIngredientCount")
    @Expose
    private Integer usedIngredientCount;
    @SerializedName("missedIngredientCount")
    @Expose
    private Integer missedIngredientCount;
    @SerializedName("likes")
    @Expose
    private Integer likes;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Integer getUsedIngredientCount() {
        return usedIngredientCount;
    }

    public void setUsedIngredientCount(Integer usedIngredientCount) {
        this.usedIngredientCount = usedIngredientCount;
    }

    public Integer getMissedIngredientCount() {
        return missedIngredientCount;
    }

    public void setMissedIngredientCount(Integer missedIngredientCount) {
        this.missedIngredientCount = missedIngredientCount;
    }

    public Integer getLikes() {
        return likes;
    }

    public void setLikes(Integer likes) {
        this.likes = likes;
    }


    // Allows this class to be passed as a parcel -> very fast
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        if (id != null)
            parcel.writeInt(id);
        else
            parcel.writeInt(0);
        parcel.writeString(title);
        if(missedIngredientCount != null)
        parcel.writeInt(missedIngredientCount);
        else
            parcel.writeInt(0);
        if(usedIngredientCount != null)
        parcel.writeInt(usedIngredientCount);
        else
            parcel.writeInt(0);
        parcel.writeString(image);
    }

    private IngredientsModel(Parcel in) {
        id = in.readInt();
        title = in.readString();
        missedIngredientCount = in.readInt();
        usedIngredientCount = in.readInt();
        image = in.readString();
    }

    // Default constructor is needed when also having a private constructor
    public IngredientsModel() {
    }


    public static final Parcelable.Creator<IngredientsModel> CREATOR
            = new Parcelable.Creator<IngredientsModel>() {

        // This simply calls our new constructor (typically private) and
        // passes along the unmarshalled `Parcel`, and then returns the new object!
        @Override
        public IngredientsModel createFromParcel(Parcel in) {
            return new IngredientsModel(in);
        }

        @Override
        public IngredientsModel[] newArray(int size) {
            return new IngredientsModel[size];
        }
    };
}
