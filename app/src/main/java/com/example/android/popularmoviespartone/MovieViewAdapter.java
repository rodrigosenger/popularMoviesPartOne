package com.example.android.popularmoviespartone;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.example.android.popularmoviespartone.data.Movie;
import com.example.android.popularmoviespartone.utilities.NetworkUtils;
import com.squareup.picasso.Picasso;
import java.util.List;

/**
 * Created by Rodrigo on 12/01/2018.
 */

public class MovieViewAdapter extends RecyclerView.Adapter<MovieViewAdapter.MovieHolder> {
    private LayoutInflater mLayoutInflater;
    private List<Movie> mMoviesData;
    private Context mContext;

    public MovieViewAdapter(Context context, List<Movie> movies) {
        mMoviesData = movies;
        mContext = context;
    }

    @Override
    public MovieHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mLayoutInflater = LayoutInflater.from(mContext);
        View view = mLayoutInflater.inflate(R.layout.movie_list_item, parent, false);

        return new MovieHolder(view);
    }

    @Override
    public void onBindViewHolder(MovieHolder holder, final int position) {
        Picasso.with(mContext)
                .load(NetworkUtils.getMoviePoster(mMoviesData.get(position).getPosterPath()))
                .into(holder.moviePosterView);
        holder.moviePosterView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, MovieDetailActivity.class);
                intent.putExtra(Movie.class.getSimpleName(), mMoviesData.get(position));
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mMoviesData.size();
    }

    public class MovieHolder extends RecyclerView.ViewHolder {
        private ImageView moviePosterView;

        public MovieHolder(View itemView) {
            super(itemView);
            moviePosterView = itemView.findViewById(R.id.movie_poster);
        }
    }

    public void clearMoviesList() {
        mMoviesData.clear();
        notifyDataSetChanged();
    }

    public void addMoviesToList(List<Movie> movies) {
        mMoviesData.addAll(movies);
        notifyDataSetChanged();
    }
}
