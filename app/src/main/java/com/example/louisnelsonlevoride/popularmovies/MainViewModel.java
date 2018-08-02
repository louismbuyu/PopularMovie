package com.example.louisnelsonlevoride.popularmovies;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.util.Log;

import com.example.louisnelsonlevoride.popularmovies.DBData.AppDatabase;
import com.example.louisnelsonlevoride.popularmovies.MoviesData.Movie;

import java.util.List;

public class MainViewModel extends AndroidViewModel {

    private static final String TAG = MainViewModel.class.getSimpleName();

    private LiveData<List<Movie>> movies;
    private AppDatabase database;

    public MainViewModel(Application application) {
        super(application);
        database = AppDatabase.getInstance(this.getApplication());
        movies = database.MovieDao().loadAllMovies();
    }

    public LiveData<List<Movie>> getMovies() {
        return movies;
    }

    public LiveData<Movie> getMovie(int id) {
        return database.MovieDao().loadMovieById(id);
    }
}


