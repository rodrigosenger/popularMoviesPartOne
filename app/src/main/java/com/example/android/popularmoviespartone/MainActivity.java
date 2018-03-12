package com.example.android.popularmoviespartone;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import com.example.android.popularmoviespartone.data.Movie;

import com.example.android.popularmoviespartone.utilities.NetworkUtils;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<Movie>>, NavigationView.OnNavigationItemSelectedListener{

    private ProgressBar mLoading;
    private RecyclerView mMovieList;
    private MovieViewAdapter mMovieViewAdapter;
    private Loader<List<Movie>> mMovieLoader;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;
    private NavigationView mNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setNavigationViewListener();
        mNavigationView.bringToFront();

        mDrawerLayout = findViewById(R.id.drawer);
        mToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.open, R.string.close);
        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mLoading = findViewById(R.id.movie_list_loading);
        mMovieList = findViewById(R.id.movie_list_recycler_view);
        mMovieList.setLayoutManager(new GridLayoutManager(this, 3));
        mMovieList.setHasFixedSize(true);
        mMovieViewAdapter = new MovieViewAdapter(this, new ArrayList<Movie>());
        mMovieList.setAdapter(mMovieViewAdapter);

        getSupportLoaderManager().initLoader(1, null, this);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_filter_popular:
                getSupportLoaderManager().restartLoader(0, null, this);
                break;
            case R.id.action_filter_top_rated:
                getSupportLoaderManager().restartLoader(1, null, this);
                break;
        }

        mDrawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mToggle.onOptionsItemSelected(item)) {
            return true;
        }

        switch (item.getItemId()) {
            case R.id.action_filter_popular:
                getSupportLoaderManager().restartLoader(0, null, this);
                return true;
            case R.id.action_filter_top_rated:
                getSupportLoaderManager().restartLoader(1, null, this);
                return true;
            default:
                super.onOptionsItemSelected(item);
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public Loader<List<Movie>> onCreateLoader(int loaderId, Bundle args) {
        String movieType;
        switch (loaderId) {
            case 0:
                movieType = "top_rated";
                break;
            default:
                movieType = "popular";
        }
        String requestUrlForMovieList = NetworkUtils.buildUrl(movieType).toString();
        mMovieLoader = new MovieLoader(this, requestUrlForMovieList);
        return mMovieLoader;
    }

    @Override
    public void onLoadFinished(Loader<List<Movie>> loader, List<Movie> movies) {
        mLoading.setVisibility(View.INVISIBLE);
        mMovieViewAdapter.clearMoviesList();
        mMovieViewAdapter.addMoviesToList(movies);
    }

    @Override
    public void onLoaderReset(Loader<List<Movie>> loader) {
        loader = null;
    }

    public void setNavigationViewListener() {
        mNavigationView = findViewById(R.id.navigation_view);
        mNavigationView.setNavigationItemSelectedListener(this);
    }
}
