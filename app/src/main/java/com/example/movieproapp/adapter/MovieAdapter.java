package com.example.movieproapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movieproapp.MovieActivity;
import com.example.movieproapp.R;
import com.example.movieproapp.databinding.MovieListItemBinding;
import com.example.movieproapp.model.Movie;

import java.util.ArrayList;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder>  {

    private final Context context;
    private ArrayList<Movie> movies  = new ArrayList<Movie>();

    public MovieAdapter(Context context, ArrayList<Movie> movies) {
        this.context = context;
        this.movies = movies;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        MovieListItemBinding movieListItemBinding  = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.movie_list_item, parent, false);

        return  new MovieViewHolder(movieListItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {

        Movie movie =  movies.get(position);
        holder.movieListItemBinding.setMovie(movie);

    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public  class MovieViewHolder extends RecyclerView.ViewHolder{

        private final MovieListItemBinding movieListItemBinding;

        public MovieViewHolder(MovieListItemBinding  movieListItemBinding) {
            super(movieListItemBinding.getRoot());
            this.movieListItemBinding = movieListItemBinding;
            movieListItemBinding.getRoot().setOnClickListener( v ->{

                int position = getAdapterPosition();

                if(position != RecyclerView.NO_POSITION){
                    Movie selectedMovie = movies.get(position);
                    Intent i = new Intent(context , MovieActivity.class);
                    i.putExtra("movie" , selectedMovie);
                    context.startActivity(i);
                }

            });
        }
    }
}
