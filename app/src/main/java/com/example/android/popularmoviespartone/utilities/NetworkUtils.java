package com.example.android.popularmoviespartone.utilities;

import android.net.Uri;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Scanner;
import com.example.android.popularmoviespartone.data.Movie;

import org.json.JSONException;


/**
 * Created by Rodrigo on 12/01/2018.
 */

public class NetworkUtils {

    private static final String TAG = NetworkUtils.class.getSimpleName();

    private static final String MOVIES_BASE_URL =
            "http://api.themoviedb.org/3/movie/";

    private static final String MOVIE_POSTER_URL =
            "https://image.tmdb.org/t/p/w185";

    private static final String POPULAR_MOVIES_URL =
            "http://api.themoviedb.org/3/movie/popular";

    private static final String TOP_RATED_MOVIES_URL =
            "http://api.themoviedb.org/3/movie/top_rated";

    private static final String apiKey = "db9e694b026cc40c9a205509cc6e40ee";

    final static String APIKEY_PARAM = "api_key";
    final static String PAGE_PARAM = "page";
    final static String REGION_PARAM = "region";
    final static String LANGUAGE_PARAM = "language";

    public static URL buildUrl (String type) {
        Uri builtUri = Uri.parse(MOVIES_BASE_URL + type).buildUpon()
                .appendQueryParameter(APIKEY_PARAM, apiKey)
                .build();

        URL url = null;
        try {
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        Log.v(TAG, "Build URI" + url);

        return url;
    }

    public static URL buildUrl (String type, String page, String region, String language) {
        Uri builtUri = Uri.parse(MOVIES_BASE_URL + type).buildUpon()
                .appendQueryParameter(APIKEY_PARAM, apiKey)
                .appendQueryParameter(PAGE_PARAM, page)
                .appendQueryParameter(REGION_PARAM, region)
                .appendQueryParameter(LANGUAGE_PARAM, language)
                .build();

        URL url = null;
        try {
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        Log.v(TAG, "Build URI" + url);

        return url;
    }

    public static String getResponseFromHttpUrl (URL url) throws IOException {
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

    public static String getMoviePoster(String posterPath) {
        Uri.Builder uriBuilder = Uri.parse(MOVIE_POSTER_URL)
                .buildUpon()
                .appendPath(posterPath);
        return uriBuilder.toString();
    }

    public static List<Movie> getMovieData(String requestUrl) throws IOException, JSONException {
        URL url = new URL(requestUrl);
        String jsonResponse;
        jsonResponse = getResponseFromHttpUrl(url);
        List<Movie> movies = OpenMoviesJsonUtils.getSimpleMovieFromJson(jsonResponse);

        return movies;
    }
}
