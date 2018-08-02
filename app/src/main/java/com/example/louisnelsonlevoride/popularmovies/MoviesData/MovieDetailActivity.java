package com.example.louisnelsonlevoride.popularmovies.MoviesData;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.louisnelsonlevoride.popularmovies.AppExecutors;
import com.example.louisnelsonlevoride.popularmovies.DBData.AppDatabase;
import com.example.louisnelsonlevoride.popularmovies.R;
import com.example.louisnelsonlevoride.popularmovies.ReviewData.ReviewActivity;
import com.example.louisnelsonlevoride.popularmovies.TrailerData.TrailerActivity;

public class MovieDetailActivity extends AppCompatActivity {

    private ImageView mainImageV;
    private TextView titleTV;
    private TextView ratingTV;
    private TextView releaseDateTV;
    private TextView plotTV;
    private final static String IMAGE_BASE_URL = "http://image.tmdb.org/t/p/w200";
    private Movie movie;
    private Button saveButton;
    private Boolean saved;
    private AppDatabase mDb;
    private final static String TAG = "MovieDetailActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        saveButton = findViewById(R.id.favouriteBtn);
        mainImageV = findViewById(R.id.main_image_iv);
        titleTV = findViewById(R.id.title_tv);
        ratingTV = findViewById(R.id.rating_tv);
        releaseDateTV = findViewById(R.id.release_date_tv);
        plotTV = findViewById(R.id.plot_tv);
        mDb = AppDatabase.getInstance(getApplicationContext());
        movie = getIntent().getParcelableExtra("movie_item");
        RequestOptions options = new RequestOptions()
                .centerCrop()
                .placeholder(R.mipmap.ic_launcher_round)
                .error(R.mipmap.ic_launcher_round);
        String mainFullImageUrl = IMAGE_BASE_URL+movie.imageUrl;
        Glide.with(this).load(mainFullImageUrl).apply(options).into(mainImageV);
        titleTV.setText(movie.getTitle());
        releaseDateTV.setText(movie.getReleaseDate());
        ratingTV.setText(""+movie.getVoteAverage());
        plotTV.setText(movie.getPlot());
        saved = false;

        final LiveData<Movie> movieLiveData = mDb.MovieDao().loadMovieByMovieId(movie.getMovieId());
        movieLiveData.observe(this, new Observer<Movie>() {
            @Override
            public void onChanged(@Nullable final Movie movieDB) {
                movieLiveData.removeObserver(this);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (movieDB != null){
                            movie.setId(movieDB.getId());
                            saved = true;
                            saveButton.setText("Favourite");
                        }else{
                            saved = false;
                            saveButton.setText("Mark as favourite");
                        }
                    }
                });
            }
        });
    }

    public void viewTrailerOne(View view) {
        Intent intent = new Intent(this, TrailerActivity.class);
        intent.putExtra("movieId", movie.getMovieId());
        this.startActivity(intent);
    }

    public void viewReviews(View view) {
        Intent intent = new Intent(this, ReviewActivity.class);
        intent.putExtra("movieId", movie.getMovieId());
        this.startActivity(intent);
    }

    public void onClickAddMovie(View view) {
        if (saved){
            AppExecutors.getInstance().diskIO().execute(new Runnable() {
                @Override
                public void run() {
                    mDb.MovieDao().deleteMovie(movie);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            saveButton.setText("Mark as favourite");
                            saved = false;
                        }
                    });
                }
            });
        }else{
            AppExecutors.getInstance().diskIO().execute(new Runnable() {
                @Override
                public void run() {
                    mDb.MovieDao().insertTask(movie);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            saveButton.setText("Favourite");
                            saved = true;
                        }
                    });
                }
            });
        }

    }
}
