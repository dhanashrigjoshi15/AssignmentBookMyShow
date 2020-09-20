package com.assignmentbookmyshow.data.model;

import com.google.gson.annotations.SerializedName;

public class MovieData {
    @SerializedName("poster_path")
    public String posterPath;
    @SerializedName("release_date")
    public String releaseDate;
    @SerializedName("title")
    public String title;
    @SerializedName("original_language")
    public String originalLanguage;
    @SerializedName("popularity")
    public float popularity;
    @SerializedName("id")
    public int id;

}
