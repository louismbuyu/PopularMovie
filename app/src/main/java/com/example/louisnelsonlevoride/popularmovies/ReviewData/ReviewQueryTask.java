package com.example.louisnelsonlevoride.popularmovies.ReviewData;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.louisnelsonlevoride.popularmovies.Services.MoviesDBUtils;
import com.example.louisnelsonlevoride.popularmovies.Services.NetworkUtils;

import java.net.URL;
import java.util.List;

public class ReviewQueryTask extends AsyncTask<String, Void, List<Review>> {

    private int movieId;
    private RecyclerView recyclerView;
    private Context context;
    private ReviewRecycleViewAdapter reviewRecycleViewAdapter;

    public ReviewQueryTask(Context context, int movieId, RecyclerView recyclerView, ReviewRecycleViewAdapter reviewRecycleViewAdapter) {
        this.context = context;
        this.movieId = movieId;
        this.recyclerView = recyclerView;
        this.reviewRecycleViewAdapter = reviewRecycleViewAdapter;
    }

    @Override
    protected List<Review> doInBackground(String... urls) {
        if (urls.length == 0) {
            return null;
        }
        String reviewQuery = urls[0];
        URL reviewsUrl = NetworkUtils.buildReviewOrTrailerUrl(movieId,reviewQuery);
        try {
            String jsonReviewsResponse = NetworkUtils.getResponseFromHttpUrl(reviewsUrl);
            List<Review> reviews = MoviesDBUtils.getReviewsFromJson(jsonReviewsResponse);
            return reviews;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    protected void onPostExecute(List<Review> reviews) {
        if (reviews != null && reviews.size() != 0){
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
            recyclerView.setLayoutManager(linearLayoutManager);
            recyclerView.setHasFixedSize(true);
            recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL));
            reviewRecycleViewAdapter = new ReviewRecycleViewAdapter(context, reviews);
            recyclerView.setAdapter(reviewRecycleViewAdapter);
        }
    }
}
