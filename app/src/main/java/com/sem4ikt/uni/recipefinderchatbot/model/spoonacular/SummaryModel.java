
        package com.sem4ikt.uni.recipefinderchatbot.model.spoonacular;

        import com.google.gson.annotations.Expose;
        import com.google.gson.annotations.SerializedName;

        /**
         * Created by anton on 09-03-2017.
         */


public class SummaryModel {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("summary")
    @Expose
    private String summary;

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

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

}
