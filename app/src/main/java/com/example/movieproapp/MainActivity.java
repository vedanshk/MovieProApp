package com.example.movieproapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;

import com.example.movieproapp.adapter.MovieAdapter;
import com.example.movieproapp.databinding.ActivityMainBinding;
import com.example.movieproapp.model.Movie;
import com.example.movieproapp.viewmodel.MainActivityViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Movie> movies;
    private ActivityMainBinding activityMainBinding;
    private MainActivityViewModel mainActivityViewModel;

    private static final String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        activityMainBinding = DataBindingUtil.setContentView(this , R.layout.activity_main);
        mainActivityViewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);

        SwipeRefreshLayout swipeRefreshLayout = activityMainBinding.swipeLayout;
        swipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.teal_200));
        swipeRefreshLayout.setOnRefreshListener(this::getPopularMovies);


    }

    private void getPopularMovies() {
        mainActivityViewModel.getAllMovies().observe(this, new Observer<List<Movie>>() {
            @Override
            public void onChanged(List<Movie> movieFromLiveData) {
                movies = (ArrayList<Movie>) movieFromLiveData;
                Log.i(TAG, "onChanged: " +  movies.toString());
                ShowOnRecyclerView();
            }
        });

    }

    private void ShowOnRecyclerView() {

        RecyclerView recyclerView = activityMainBinding.rvMovies;
        MovieAdapter movieAdapter = new MovieAdapter(this, movies);
        if(this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT){
            recyclerView.setLayoutManager( new LinearLayoutManager(this));
        }else{

            recyclerView.setLayoutManager(new GridLayoutManager(this , 4));
        }

        recyclerView.setItemAnimator(new DefaultItemAnimator());

        recyclerView.setAdapter(movieAdapter);

        movieAdapter.notifyDataSetChanged();


    }
}