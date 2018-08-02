package com.example.louisnelsonlevoride.popularmovies.MoviesData;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.louisnelsonlevoride.popularmovies.RotationViewModel;
import com.example.louisnelsonlevoride.popularmovies.Services.MoviesDBUtils;
import com.example.louisnelsonlevoride.popularmovies.Services.NetworkUtils;

import java.net.URL;
import java.util.List;

public class MovieQueryTask extends AsyncTask<String, Void, List<Movie>> {

    private GridLayoutManager gridLayoutManager;
    private RecyclerView recyclerView;
    private MovieViewAdapter movieViewAdapter;
    private Context context;
    private RotationViewModel rotationViewModel;

    public MovieQueryTask(Context context,GridLayoutManager gridLayoutManager, RecyclerView recyclerView, MovieViewAdapter movieViewAdapter, RotationViewModel rotationViewModel) {
        this.context = context;
        this.gridLayoutManager = gridLayoutManager;
        this.recyclerView = recyclerView;
        this.movieViewAdapter = movieViewAdapter;
        this.rotationViewModel = rotationViewModel;
    }

    @Override
    protected List<Movie> doInBackground(String... strings) {
        if (strings.length == 0) {
            return null;
        }
        String sortQuery = strings[0];
        URL moviesUrl = NetworkUtils.buildUrl(sortQuery);
        try {
            String jsonMoviesResponse = NetworkUtils.getResponseFromHttpUrl(moviesUrl);
            List<Movie> movies = MoviesDBUtils.getMoviesFromJson(jsonMoviesResponse);
            return movies;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }



    @Override
    protected void onPostExecute(List<Movie> movies) {
        if (movies != null && movies.size() != 0){
            gridLayoutManager = new GridLayoutManager(context, 2);
            recyclerView.setLayoutManager(gridLayoutManager);
            recyclerView.setHasFixedSize(true);
            movieViewAdapter = new MovieViewAdapter(movies,context);
            rotationViewModel.movies = movies;
            recyclerView.setAdapter(movieViewAdapter);
        }
    }
}
