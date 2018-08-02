package com.example.louisnelsonlevoride.popularmovies.MoviesData;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.louisnelsonlevoride.popularmovies.R;

import java.util.List;

public class MovieViewAdapter extends RecyclerView.Adapter<MovieViewHolder>{

    List<Movie> movies;
    Context context;
    private final static String IMAGE_BASE_URL = "http://image.tmdb.org/t/p/w200";

    public MovieViewAdapter(List<Movie> movies, Context context) {
        this.movies = movies;
        this.context = context;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycle_movie_item, parent, false);
        return new MovieViewHolder(view, movies, this.context);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        RequestOptions options = new RequestOptions()
                .centerCrop()
                .placeholder(R.mipmap.ic_launcher_round)
                .error(R.mipmap.ic_launcher_round);
        String fullImageUrl = IMAGE_BASE_URL+movies.get(position).imageUrl;
        Glide.with(context).load(fullImageUrl).apply(options).into(holder.imageView);
    }

    public List<Movie> getMovies() {
        return movies;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (null == movies){
            return 0;
        }else {
            return movies.size();
        }
    }
}
