package com.example.movieproapp.model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.example.movieproapp.R;
import com.example.movieproapp.service.MovieDataService;
import com.example.movieproapp.service.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MovieRepository {

    private ArrayList<Movie> movies = new ArrayList<>();
    private MutableLiveData<List<Movie>> mutableLiveData = new MutableLiveData<>();
    private Application application;

    public MovieRepository(Application application) {
        this.application = application;
    }

    public MutableLiveData<List<Movie>> getMutableLiveData(){

        MovieDataService movieDataService = RetrofitClient.getService();
        Call<Result> call =  movieDataService.getPopularMovies(application.getResources().getString(R.string.API_KEY));

        call.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(@NonNull Call<Result> call, @NonNull Response<Result> response) {

                Result result = response.body();
                if(result != null && result.getResults() !=null){
                    movies = (ArrayList<Movie>) result.getResults();
                    mutableLiveData.setValue(movies);

                }
            }

            @Override
            public void onFailure(@NonNull Call<Result> call, @NonNull Throwable t) {

            }
        });

        return mutableLiveData;

    };
}
