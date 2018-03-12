package com.example.android.popularmoviespartone.data;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Rodrigo on 15/01/2018.
 */

public class Movie implements Parcelable{
    private String id;
    private String title;
    private String popularity;
    private String voteCount;
    private String voteAverage;
    private String posterPath;
    private String overview;
    private String releaseDate;

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public Movie createFromParcel (Parcel in) {
            return new Movie(in);
        }

        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };

    public Movie(String id, String title, String popularity, String voteCount, String voteAverage, String posterPath, String overview, String releaseDate) {
        this.id = id;
        this.title = title;
        this.popularity = popularity;
        this.voteCount = voteCount;
        this.voteAverage = voteAverage;
        this.posterPath = posterPath;
        this.overview = overview;
        this.releaseDate = releaseDate;
    }

    public Movie(Parcel in) {
        this.id = in.readString();
        this.title = in.readString();
        this.popularity = in.readString();
        this.voteCount = in.readString();
        this.voteAverage = in.readString();
        this.posterPath = in.readString();
        this.overview = in.readString();
        this.releaseDate = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.title);
        dest.writeString(this.popularity);
        dest.writeString(this.voteCount);
        dest.writeString(this.voteAverage);
        dest.writeString(this.posterPath);
        dest.writeString(this.overview);
        dest.writeString(this.releaseDate);
    }

    public String getPopularity() {
        return popularity;
    }

    public void setPopularity(String popularity) {
        this.popularity = popularity;
    }

    public String getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(String voteCount) {
        this.voteCount = voteCount;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(String voteAverage) {
        this.voteAverage = voteAverage;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }
}
