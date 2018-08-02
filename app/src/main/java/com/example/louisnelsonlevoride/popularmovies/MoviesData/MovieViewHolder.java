package com.example.louisnelsonlevoride.popularmovies.MoviesData;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.example.louisnelsonlevoride.popularmovies.R;

import java.util.List;

public class MovieViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    ImageView imageView;
    List<Movie> movies;
    Context context;

    public MovieViewHolder(View itemView, List<Movie> movies, Context context) {
        super(itemView);
        itemView.setOnClickListener(this);
        this.movies = movies;
        this.context = context;
        imageView = itemView.findViewById(R.id.movieIV_id);
    }

    @Override
    public void onClick(View view) {
        int position = getAdapterPosition();
        Movie item = movies.get(position);
        Intent intent = new Intent(context, MovieDetailActivity.class);
        intent.putExtra("movie_item", item);
        context.startActivity(intent);
    }
}
