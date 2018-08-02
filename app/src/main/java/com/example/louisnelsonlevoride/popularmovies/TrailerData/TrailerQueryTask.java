package com.example.louisnelsonlevoride.popularmovies.TrailerData;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.louisnelsonlevoride.popularmovies.ReviewData.ReviewRecycleViewAdapter;
import com.example.louisnelsonlevoride.popularmovies.Services.MoviesDBUtils;
import com.example.louisnelsonlevoride.popularmovies.Services.NetworkUtils;

import java.net.URL;
import java.util.List;

public class TrailerQueryTask extends AsyncTask<String, Void, List<Trailer>> {

    private int movieId;
    private RecyclerView recyclerView;
    private Context context;
    private TrailerRecycleViewAdapter trailerRecycleViewAdapter;

    public TrailerQueryTask(Context context, int movieId, RecyclerView recyclerView, TrailerRecycleViewAdapter trailerRecycleViewAdapter) {
        this.context = context;
        this.movieId = movieId;
        this.recyclerView = recyclerView;
        this.trailerRecycleViewAdapter = trailerRecycleViewAdapter;
    }

    @Override
    protected List<Trailer> doInBackground(String... urls) {
        if (urls.length == 0) {
            return null;
        }
        String trailerQuery = urls[0];
        URL reviewsUrl = NetworkUtils.buildReviewOrTrailerUrl(movieId,trailerQuery);
        try {
            String jsonReviewsResponse = NetworkUtils.getResponseFromHttpUrl(reviewsUrl);
            List<Trailer> trailers = MoviesDBUtils.getTrailersFromJson(jsonReviewsResponse);
            return trailers;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    protected void onPostExecute(List<Trailer> trailers) {
        if (trailers != null && trailers.size() != 0){
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
            recyclerView.setLayoutManager(linearLayoutManager);
            recyclerView.setHasFixedSize(true);
            recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL));
            trailerRecycleViewAdapter = new TrailerRecycleViewAdapter(context, trailers);
            recyclerView.setAdapter(trailerRecycleViewAdapter);
        }
    }
}
