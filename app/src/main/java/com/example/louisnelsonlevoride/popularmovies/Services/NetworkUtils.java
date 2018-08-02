package com.example.louisnelsonlevoride.popularmovies.Services;

import android.net.Uri;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public final class NetworkUtils {

    private final static String MOVIEDB_BASE_URL = "https://api.themoviedb.org/3/movie";
    private final static String PARAM_API_KEY = "api_key";
    private final static String API_KEY = "xxxxx";

    public static URL buildUrl(String sortQuery) {
        String FINAL_BASE_URL = MOVIEDB_BASE_URL + sortQuery;
        Uri builtUri = Uri.parse(FINAL_BASE_URL).buildUpon()
                .appendQueryParameter(PARAM_API_KEY, API_KEY)
                .build();

        URL url = null;
        try {
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return url;
    }

    public static URL buildReviewOrTrailerUrl(int movieId, String reviewOrTrailer) {
        String FINAL_BASE_URL = MOVIEDB_BASE_URL + "/"+movieId+"/"+reviewOrTrailer;
        Uri builtUri = Uri.parse(FINAL_BASE_URL).buildUpon()
                .appendQueryParameter(PARAM_API_KEY, API_KEY)
                .build();

        URL url = null;
        try {
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return url;
    }

    public static String getResponseFromHttpUrl(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            InputStream in = urlConnection.getInputStream();
            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");
            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                return scanner.next();
            } else {
                return null;
            }
        } finally {
            urlConnection.disconnect();
        }
    }
}
