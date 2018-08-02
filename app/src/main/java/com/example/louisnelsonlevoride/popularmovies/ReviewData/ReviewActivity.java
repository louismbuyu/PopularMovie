package com.example.louisnelsonlevoride.popularmovies.ReviewData;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.louisnelsonlevoride.popularmovies.R;
import com.example.louisnelsonlevoride.popularmovies.Services.MoviesDBUtils;
import com.example.louisnelsonlevoride.popularmovies.Services.NetworkUtils;

import java.net.URL;

public class ReviewActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ReviewRecycleViewAdapter reviewRecycleViewAdapter;
    private final static String PARAM_REVIEWS = "reviews";
    int movieId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);
        setTitle("Reviews");
        recyclerView = findViewById(R.id.reviews_recycleView);
        movieId = getIntent().getIntExtra("movieId", -1);
        if (movieId != -1) {
            new ReviewQueryTask(this,movieId,recyclerView,reviewRecycleViewAdapter).execute(PARAM_REVIEWS);
        }
    }
}
