package com.example.louisnelsonlevoride.popularmovies;

import android.arch.lifecycle.ViewModel;

import com.example.louisnelsonlevoride.popularmovies.MoviesData.Movie;
import com.example.louisnelsonlevoride.popularmovies.MoviesData.MovieViewAdapter;

import java.util.List;

public class RotationViewModel extends ViewModel {

    public int popularSwitchValue = 0;

    public List<Movie> movies;

    public MovieViewAdapter movieViewAdapter;
}