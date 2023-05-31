package com.example.movieproapp.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.movieproapp.model.Movie;
import com.example.movieproapp.model.MovieRepository;

import java.util.List;

public class MainActivityViewModel extends AndroidViewModel {

    private final MovieRepository repository;
    public MainActivityViewModel(@NonNull Application application) {
        super(application);
        repository =  new MovieRepository(application);
    }

    public LiveData<List<Movie>> getAllMovies(){

        return repository.getMutableLiveData();
    }
}
