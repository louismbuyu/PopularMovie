package com.example.louisnelsonlevoride.popularmovies;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.os.AsyncTask;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.example.louisnelsonlevoride.popularmovies.DBData.AppDatabase;
import com.example.louisnelsonlevoride.popularmovies.MoviesData.Movie;
import com.example.louisnelsonlevoride.popularmovies.MoviesData.MovieQueryTask;
import com.example.louisnelsonlevoride.popularmovies.MoviesData.MovieViewAdapter;
import com.example.louisnelsonlevoride.popularmovies.Services.MoviesDBUtils;
import com.example.louisnelsonlevoride.popularmovies.Services.NetworkUtils;

import java.net.URL;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    MovieViewAdapter movieViewAdapter;
    private final static String TAG = "MainActivity";
    private GridLayoutManager gridLayoutManager;
    private final static String PARAM_POPULAR_SORT = "/popular?";
    private final static String PARAM_VOTE_AVERAGE_SORT = "/top_rated?";
    private Integer popularSwitchValue = 0;
    private RotationViewModel rotationViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.movies_recycleView);
        rotationViewModel = ViewModelProviders.of(this).get(RotationViewModel.class);
        if (rotationViewModel.movies == null){
            recyclerView.setAdapter(movieViewAdapter);
            new MovieQueryTask(this, gridLayoutManager,recyclerView,movieViewAdapter,rotationViewModel).execute(PARAM_POPULAR_SORT);
        }else{
            popularSwitchValue = rotationViewModel.popularSwitchValue;
            gridLayoutManager = new GridLayoutManager(this, 2);
            recyclerView.setLayoutManager(gridLayoutManager);
            recyclerView.setHasFixedSize(true);
            movieViewAdapter = new MovieViewAdapter(rotationViewModel.movies,this);
            recyclerView.setAdapter(movieViewAdapter);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        MovieViewAdapter movieViewAdapter1 = (MovieViewAdapter) recyclerView.getAdapter();
        rotationViewModel.movies = movieViewAdapter1.getMovies();
        rotationViewModel.popularSwitchValue = popularSwitchValue;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.filter_item,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_filter:
                final FilterDialog alert = new FilterDialog();
                alert.showDialog(this, "Error has occurred");
                if (popularSwitchValue == 0){
                    alert.radioButtonPopular.setChecked(true);
                }else if (popularSwitchValue == 1){
                    alert.radioButtonRating.setChecked(true);
                }else {
                    alert.radioButtonFavourites.setChecked(true);
                }
                alert.applyFilter.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (alert.radioButtonPopular.isChecked()){
                            popularSwitchValue = 0;
                            new MovieQueryTask(MainActivity.this, gridLayoutManager,recyclerView,movieViewAdapter, rotationViewModel).execute(PARAM_POPULAR_SORT);
                        }else if (alert.radioButtonRating.isChecked()){
                            popularSwitchValue = 1;
                            new MovieQueryTask(MainActivity.this, gridLayoutManager,recyclerView,movieViewAdapter, rotationViewModel).execute(PARAM_VOTE_AVERAGE_SORT);
                        } else{
                            popularSwitchValue = 2;
                            getFavouriteMovies();
                        }
                        alert.dialog.dismiss();
                    }
                });
                break;
        }
        return true;
    }

    private void getFavouriteMovies() {
        MainViewModel viewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        if (viewModel != null){
            LiveData<List<Movie>> listLiveData = viewModel.getMovies();
            if (listLiveData != null){
                listLiveData.observe(this, new Observer<List<Movie>>() {
                    @Override
                    public void onChanged(@Nullable List<Movie> movies) {
                        movieViewAdapter = new MovieViewAdapter(movies,MainActivity.this);
                        recyclerView.setAdapter(movieViewAdapter);
                    }
                });
            }
        }

    }
}