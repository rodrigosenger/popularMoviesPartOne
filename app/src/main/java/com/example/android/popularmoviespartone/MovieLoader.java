package com.example.android.popularmoviespartone;

import android.support.v4.content.AsyncTaskLoader;
import android.content.Context;

import com.example.android.popularmoviespartone.data.Movie;
import com.example.android.popularmoviespartone.utilities.NetworkUtils;

import org.json.JSONException;

import java.io.IOException;
import java.util.List;

/**
 * Created by Rodrigo on 15/01/2018.
 */

public class MovieLoader extends AsyncTaskLoader<List<Movie>> {
    String mUrl;

    public MovieLoader(Context context, String url) {
        super(context);
        mUrl = url;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public List<Movie> loadInBackground() {
        List<Movie> movieData = null;
        try {
            movieData = NetworkUtils.getMovieData(mUrl);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return movieData;
    }
}
