package com.example.android.popularmoviespartone;

import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.popularmoviespartone.data.Movie;
import com.example.android.popularmoviespartone.utilities.NetworkUtils;
import com.squareup.picasso.Picasso;

/**
 * Created by Rodrigo on 16/01/2018.
 */

public class MovieDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        ImageView posterMovieView = findViewById(R.id.movie_poster_detail);
        TextView titleTextView = findViewById(R.id.movie_title_detail);
        TextView releaseDateTextView = findViewById(R.id.movie_release_date_detail);
        TextView voteAvgTextView = findViewById(R.id.movie_vote_avg_detail);
        TextView overviewTextView = findViewById(R.id.movie_overview_detail);

        Movie currentMovie = getIntent().getExtras().getParcelable(Movie.class.getSimpleName());
        String requestUrlForPoster = NetworkUtils.getMoviePoster(currentMovie.getPosterPath());

        Picasso.with(this)
                .load(requestUrlForPoster)
                .into(posterMovieView);

        titleTextView.setText(currentMovie.getTitle());
        releaseDateTextView.setText(currentMovie.getReleaseDate());
        voteAvgTextView.setText(currentMovie.getVoteAverage());
        overviewTextView.setText(currentMovie.getOverview());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
