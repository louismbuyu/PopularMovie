package com.example.louisnelsonlevoride.popularmovies.TrailerData;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.example.louisnelsonlevoride.popularmovies.R;

public class TrailerActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    TrailerRecycleViewAdapter trailerRecycleViewAdapter;
    private final static String PARAM_VIDEOS = "videos";
    int movieId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trailer);

        recyclerView = findViewById(R.id.trailers_recycleView);
        movieId = getIntent().getIntExtra("movieId", -1);
        if (movieId != -1){
            new TrailerQueryTask(this,movieId,recyclerView,trailerRecycleViewAdapter).execute(PARAM_VIDEOS);
        }
    }
}
