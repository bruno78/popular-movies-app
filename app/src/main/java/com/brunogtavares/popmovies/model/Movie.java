package com.brunogtavares.popmovies.model;

/**
 * Created by brunogtavares on 5/10/18.
 */

public class Movie {
    private String title;
    private String poster;
    private String backDrop;
    private String synopsis;
    private double rating;
    private String releaseDate;

    public Movie( String originalTitle, String posterImage, String backDrop, String overview, double voteAverage, String relesaseDate ) {
        this.title = originalTitle;
        this.poster = posterImage;
        this.backDrop = backDrop;
        this.synopsis = overview;
        this.rating = voteAverage;
        this.releaseDate = relesaseDate;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getBackDrop() { return backDrop; }

    public void setBackDrop(String backDrop) { this.backDrop = backDrop; }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }
}
