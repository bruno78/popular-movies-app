package com.brunogtavares.popmovies.utils;

import android.util.Log;

import com.brunogtavares.popmovies.model.Movie;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

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

    public static ArrayList<Movie> extractMovies() {

        ArrayList<Movie> movies = new ArrayList<>();

        try {

            JSONObject moviesObject = extractJson();
            JSONArray results = moviesObject.getJSONArray("results");

            for (int i = 0; i < results.length(); i++) {

                JSONObject movieJson = results.getJSONObject(i);

                String title = movieJson.getString("original_title");
                String posterPath = movieJson.getString("poster_path");
                String synopsis = movieJson.getString("overview");
                double rating = movieJson.getDouble("vote_average");
                String releaseDate = movieJson.getString("release_date");

                movies.add(new Movie(title, posterPath, synopsis, rating, releaseDate));

            }

        } catch (FileNotFoundException e) {
            Log.e(LOG_TAG, "Error handling file", e);
        } catch (JSONException e) {
            Log.e(LOG_TAG, "Error handling JSON file", e);
        }

        return movies;
    }
    /**
     * This is a helper temporary method just to handle mock data
     * @return JSON object
     * @throws FileNotFoundException
     * @throws JSONException
     */
    private static JSONObject extractJson() throws FileNotFoundException, JSONException {
        File file = new File("./moviesDataMock.json");
        StringBuilder text = new StringBuilder();
        BufferedReader br = null;

        try {
            br = new BufferedReader(new FileReader(file));
            String line;

            while((line = br.readLine()) != null) {
                text.append(line);
                text.append('\n');
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try { br.close(); } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return new JSONObject(text.toString());
    }
}
