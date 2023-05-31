package com.example.movieproapp.service;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static final String BASE_URL = "https://api.themoviedb.org/3/";

    private static  Retrofit retrofit = null;

    private RetrofitClient() {
        // Private constructor to prevent instantiation
    }

    public static synchronized MovieDataService getService() {

        if (retrofit == null) {

                    Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl(BASE_URL)
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();

                }
        return retrofit.create(MovieDataService.class);

    }
}
