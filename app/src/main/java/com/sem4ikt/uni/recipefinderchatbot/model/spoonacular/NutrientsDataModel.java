package com.sem4ikt.uni.recipefinderchatbot.model.spoonacular;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.sem4ikt.uni.recipefinderchatbot.adapter.ListDataModel;

/**
 * Created by mathiaslykkepedersen on 06/03/2017.
 */

public class NutrientsDataModel implements Parcelable, ListDataModel {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("imageType")
    @Expose
    private String imageType;
    @SerializedName("calories")
    @Expose
    private Integer calories;
    @SerializedName("protein")
    @Expose
    private Integer protein;
    @SerializedName("fat")
    @Expose
    private Integer fat;
    @SerializedName("carbs")
    @Expose
    private Integer carbs;

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

    public String getImageType() {
        return imageType;
    }

    public void setImageType(String imageType) {
        this.imageType = imageType;
    }

    public Integer getCalories() {
        return calories;
    }

    public void setCalories(Integer calories) {
        this.calories = calories;
    }

    public Integer getProtein() {
        return protein;
    }

    public void setProtein(Integer protein) {
        this.protein = protein;
    }

    public Integer getFat() {
        return fat;
    }

    public void setFat(Integer fat) {
        this.fat = fat;
    }

    public Integer getCarbs() {
        return carbs;
    }

    public void setCarbs(Integer carbs) {
        this.carbs = carbs;
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
        parcel.writeString(image);
    }

    @SuppressWarnings("all")
    private NutrientsDataModel(Parcel in) {
        id = in.readInt();
        title = in.readString();
        image = in.readString();
    }

    // Default constructor is needed when also having a private constructor
    public NutrientsDataModel() {
    }


    public static final Parcelable.Creator<NutrientsDataModel> CREATOR
            = new Parcelable.Creator<NutrientsDataModel>() {

        // This simply calls our new constructor (typically private) and
        // passes along the unmarshalled `Parcel`, and then returns the new object!
        @Override
        public NutrientsDataModel createFromParcel(Parcel in) {
            return new NutrientsDataModel(in);
        }

        @Override
        public NutrientsDataModel[] newArray(int size) {
            return new NutrientsDataModel[size];
        }
    };
}