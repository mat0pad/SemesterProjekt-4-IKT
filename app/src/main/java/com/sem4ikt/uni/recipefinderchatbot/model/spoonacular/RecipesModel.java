package com.sem4ikt.uni.recipefinderchatbot.model.spoonacular;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by mathiaslykkepedersen on 06/03/2017.
 */

public class RecipesModel implements Parcelable {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("readyInMinutes")
    @Expose
    private Integer readyInMinutes;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("imageUrls")
    @Expose
    private List<String> imageUrls = null;

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

    public Integer getReadyInMinutes() {
        return readyInMinutes;
    }

    public void setReadyInMinutes(Integer readyInMinutes) {
        this.readyInMinutes = readyInMinutes;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public List<String> getImageUrls() {
        return imageUrls;
    }

    public void setImageUrls(List<String> imageUrls) {
        this.imageUrls = imageUrls;
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
        if (readyInMinutes != null)
            parcel.writeInt(readyInMinutes);
        else
            parcel.writeInt(0);
        parcel.writeString(title);
        parcel.writeString(image);
        parcel.writeStringList(imageUrls);
    }

    @SuppressWarnings("all")
    private RecipesModel(Parcel in) {
        id = in.readInt();
        readyInMinutes = in.readInt();
        title = in.readString();
        image = in.readString();
        imageUrls = in.createStringArrayList();
    }

    // Default constructor is needed when also having a private constructor
    public RecipesModel() {
    }


    public static final Parcelable.Creator<RecipesModel> CREATOR
            = new Parcelable.Creator<RecipesModel>() {

        // This simply calls our new constructor (typically private) and
        // passes along the unmarshalled `Parcel`, and then returns the new object!
        @Override
        public RecipesModel createFromParcel(Parcel in) {
            return new RecipesModel(in);
        }

        @Override
        public RecipesModel[] newArray(int size) {
            return new RecipesModel[size];
        }
    };
}
