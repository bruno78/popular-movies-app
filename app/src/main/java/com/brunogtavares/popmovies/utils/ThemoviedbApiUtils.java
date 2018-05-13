package com.brunogtavares.popmovies.utils;

import android.content.res.AssetManager;
import android.content.res.Resources;
import android.util.Log;

import com.brunogtavares.popmovies.R;
import com.brunogtavares.popmovies.model.Movie;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

/**
 * Created by brunogtavares on 5/10/18.
 */

public class ThemoviedbApiUtils {

    private static final String LOG_TAG = ThemoviedbApiUtils.class.getSimpleName();
    // title
    // poster
    // synopsis
    // rating
    // releaseDate

    private ThemoviedbApiUtils() {
    }

    public static ArrayList<Movie> extractMovies(JSONObject moviesObject) {

        ArrayList<Movie> movies = new ArrayList<>();

        try {

            JSONArray results = moviesObject.getJSONArray("results");

            for (int i = 0; i < results.length(); i++) {

                JSONObject movieJson = results.getJSONObject(i);

                String title = movieJson.getString("original_title");
                String posterPath = buildPosterPath(movieJson.getString("poster_path"));
                String backDrop = buildPosterPath(movieJson.getString("back_drop"));
                String synopsis = movieJson.getString("overview");
                double rating = movieJson.getDouble("vote_average");
                String releaseDate = movieJson.getString("release_date");

                movies.add(new Movie(title, posterPath, backDrop, synopsis, rating, releaseDate));

            }

        }  catch (JSONException e) {
            Log.e(LOG_TAG, "Error handling JSON file", e);
        }

        return movies;
    }

    private static String buildPosterPath(String moviePath) {
        // w185 for small devices
        // w500 for tablet
        return "http://image.tmdb.org/t/p/" + "w500/" + moviePath;
    }
}
