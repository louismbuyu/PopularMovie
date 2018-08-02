package com.example.louisnelsonlevoride.popularmovies.Services;

import com.example.louisnelsonlevoride.popularmovies.MoviesData.Movie;
import com.example.louisnelsonlevoride.popularmovies.ReviewData.Review;
import com.example.louisnelsonlevoride.popularmovies.TrailerData.Trailer;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public final class MoviesDBUtils {

    public static List<Movie> getMoviesFromJson(String moviesResponseJsonStr)
            throws JSONException {
        JSONObject moviesResponseJson = new JSONObject(moviesResponseJsonStr);
        JSONArray results = moviesResponseJson.getJSONArray("results");
        List<Movie> movies = new ArrayList<>();
        for (int i = 0; i < results.length(); i++) {
            JSONObject result = results.getJSONObject(i);
            Integer id = result.getInt("id");
            String title = result.getString("title");
            Double rating = result.getDouble("vote_average");
            Double popularity = result.getDouble("popularity");
            String imageUrlExtensionPath = result.getString("poster_path");
            String backgoundImageUrlExtenPath = result.getString("backdrop_path");
            String releaseDate = result.getString("release_date");
            String overview = result.getString("overview");
            Movie newMovie = new Movie(id,title,rating,popularity,imageUrlExtensionPath,backgoundImageUrlExtenPath,releaseDate,overview);
            movies.add(newMovie);
        }

        return movies;
    }

    public static List<Review> getReviewsFromJson(String reviewResponseJsonStr)
            throws JSONException {
        JSONObject reviewsResponseJson = new JSONObject(reviewResponseJsonStr);
        JSONArray results = reviewsResponseJson.getJSONArray("results");
        List<Review> reviews = new ArrayList<>();
        for (int i = 0; i < results.length(); i++) {
            JSONObject result = results.getJSONObject(i);
            String author = result.getString("author");
            String reviewContent = result.getString("content");
            Review newReview = new Review(author,reviewContent);
            reviews.add(newReview);
        }
        return reviews;
    }

    public static List<Trailer> getTrailersFromJson(String reviewResponseJsonStr)
            throws JSONException {
        JSONObject reviewsResponseJson = new JSONObject(reviewResponseJsonStr);
        JSONArray results = reviewsResponseJson.getJSONArray("results");
        List<Trailer> trailers = new ArrayList<>();
        for (int i = 0; i < results.length(); i++) {
            JSONObject result = results.getJSONObject(i);
            String key = result.getString("key");
            String trailerName = result.getString("name");
            String trailerType = result.getString("type");
            String trailerSite = result.getString("site");
            Trailer newTrailer = new Trailer(key,trailerName,trailerType,trailerSite);
            trailers.add(newTrailer);
        }
        return trailers;
    }
}
