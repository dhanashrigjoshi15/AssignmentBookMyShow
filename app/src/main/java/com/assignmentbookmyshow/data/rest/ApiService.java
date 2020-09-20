package com.assignmentbookmyshow.data.rest;

import com.assignmentbookmyshow.data.model.MovieData;

import java.util.List;

import io.reactivex.Single;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {
    @Headers({
            "Content-Type: application/json",
            "Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJiNzRiNWI5ZWZkOTY1OTYwMjllMzE1ZTRkMjRhYjNlNCIsInN1YiI6IjVmNGUwMWZmOWVjZjE4MDAzNjkyZWQ4NiIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.pQK42HUWhk8nxOsEvyaSdK_MHqks85mARn7kxfiQEro"
    })
    @GET("/4/list/1?")
    Call<List<MovieData>> getMovies(@Query("page") String page, @Query("api_key") String apiKey);

    @GET("repos/{owner}/{name}")
    Call<MovieData> getRepo(@Path("owner") String owner, @Path("name") String name);
}
