package com.sem4ikt.uni.recipefinderchatbot.model.spoonacular;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.sem4ikt.uni.recipefinderchatbot.model.interfaces.ListDataModel;

/**
 * Created by mathiaslykkepedersen on 06/03/2017.
 */

public class NutrientsModel implements Parcelable, ListDataModel {

    public static final Parcelable.Creator<NutrientsModel> CREATOR
            = new Parcelable.Creator<NutrientsModel>() {

        // This simply calls our new constructor (typically private) and
        // passes along the unmarshalled `Parcel`, and then returns the new object!
        @Override
        public NutrientsModel createFromParcel(Parcel in) {
            return new NutrientsModel(in);
        }

        @Override
        public NutrientsModel[] newArray(int size) {
            return new NutrientsModel[size];
        }
    };
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
    private String protein;
    @SerializedName("fat")
    @Expose
    private String fat;
    @SerializedName("carbs")
    @Expose
    private String carbs;


    // Default constructor is needed when also having a private constructor
    public NutrientsModel() {
    }

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

    public String getProtein() {
        return protein;
    }

    public void setProtein(String protein) {
        this.protein = protein;
    }

    public String getFat() {
        return fat;
    }

    public void setFat(String fat) {
        this.fat = fat;
    }

    public String getCarbs() {
        return carbs;
    }

    public void setCarbs(String carbs) {
        this.carbs = carbs;
    }


    // Allows this class to be passed as a parcel -> very fast
    private NutrientsModel(Parcel in) {
        id = in.readInt();
        title = in.readString();
        image = in.readString();
        calories = in.readInt();
        carbs = in.readString();
        protein = in.readString();
    }

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
        if (calories != null)
        parcel.writeInt(calories);
        else
            parcel.writeInt(0);
        parcel.writeString(carbs);
        parcel.writeString(protein);
    }
}