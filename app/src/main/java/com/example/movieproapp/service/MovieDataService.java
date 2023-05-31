package com.example.movieproapp.service;

import com.example.movieproapp.model.Result;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MovieDataService {

    // End_Point
    @GET("movie/popular")
    Call<Result> getPopularMovies(
            @Query("api_key") String apiKey
    );
}
