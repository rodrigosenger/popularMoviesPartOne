package com.example.android.popularmoviespartone.utilities;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.android.popularmoviespartone.data.Movie;

import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rodrigo on 12/01/2018.
 */

public class OpenMoviesJsonUtils {

    public static List<Movie> getSimpleMovieFromJson(String movieJsonStr) throws JSONException {
        final String OWN_LIST = "results";
        final String OWN_VOTE_COUNT = "vote_count";
        final String OWN_ID = "id";
        final String OWN_VOTE_AVERAGE = "vote_average";
        final String OWN_TITLE = "title";
        final String OWN_POPULARITY = "popularity";
        final String OWN_POSTER_PATH = "poster_path";
        final String OWN_RELEASE_DATE = "release_date";
        final String OWN_OVERVIEW = "overview";
        final String OWM_MESSAGE_CODE = "cod";

        JSONObject movieJson = new JSONObject(movieJsonStr);

        if (movieJson.has(OWN_LIST)) {
            JSONArray movieArray = movieJson.getJSONArray(OWN_LIST);

            List<Movie> parsedMovies = new ArrayList<>();

            for (int i = 0; i < movieArray.length(); i++) {
                String id;
                String title;
                String voteCount;
                String voteAverage;
                String popularity;
                String posterPath;
                String overview;
                String releaseDate;

                JSONObject movie = movieArray.getJSONObject(i);

                id = movie.getString(OWN_ID);
                title = movie.getString(OWN_TITLE);
                popularity = movie.getString(OWN_POPULARITY);
                voteCount = movie.getString(OWN_VOTE_COUNT);
                voteAverage = movie.getString(OWN_VOTE_AVERAGE);
                posterPath = movie.getString(OWN_POSTER_PATH).substring(1);
                overview = movie.getString(OWN_OVERVIEW);
                releaseDate = movie.getString(OWN_RELEASE_DATE);

                parsedMovies.add(new Movie(id, title, popularity, voteCount, voteAverage, posterPath, overview, releaseDate));
            }

            return parsedMovies;
        } else {
            return null;
        }
    }
}
