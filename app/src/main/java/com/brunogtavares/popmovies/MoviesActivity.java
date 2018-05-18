package com.brunogtavares.popmovies;

import android.content.res.AssetManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.ProgressBar;

import com.brunogtavares.popmovies.model.Movie;
import com.brunogtavares.popmovies.utils.ThemoviedbApiUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class MoviesActivity extends AppCompatActivity {

    private MovieAdapter movieAdapter;
    private RecyclerView mRecyclerView;

    private ProgressBar mLoadingIndicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies);

        mRecyclerView = (RecyclerView) findViewById(R.id.rv_movie_grid);
       // mLoadingIndicator = (ProgressBar) findViewById(R.id.pb_loading_indicator);

        ArrayList<Movie> movies = new ArrayList<>();

        try {
            movies = ThemoviedbApiUtils.extractMovies(getJSON());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        if(movies.size() > 0) {
            Log.d("MoviesActivity", "Movies size: " + movies.size());

            GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
            mRecyclerView.setLayoutManager(gridLayoutManager);
            mRecyclerView.setHasFixedSize(true);

            MovieAdapter movieAdapter = new MovieAdapter(this, movies);
            mRecyclerView.setAdapter(movieAdapter);
        }

    }

    private JSONObject getJSON() throws JSONException {

        String json = null;
        try {
            // AssetManager assetManager = this.getAssets();
            InputStream is = getAssets().open("movies_data_mock.json");

            StringBuilder buf = new StringBuilder();
            BufferedReader in = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            String str;

            while ((str=in.readLine()) != null) {
                buf.append(str);
            }

            in.close();
            json = buf.toString();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return new JSONObject(json.toString());
    }
}
