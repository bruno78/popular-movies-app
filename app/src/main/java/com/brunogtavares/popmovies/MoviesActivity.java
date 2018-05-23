package com.brunogtavares.popmovies;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.brunogtavares.popmovies.model.Movie;

import java.util.ArrayList;
import java.util.List;

public class MoviesActivity extends AppCompatActivity
    implements LoaderManager.LoaderCallbacks<List<Movie>>, MovieAdapter.MovieAdapterOnClickHandler {

    private static final String LOG_TAG = MoviesActivity.class.getName();
    private static final String MOVIE_BUNDLE_KEY = "MOVIE_KEY";
    private static final String MOVIES_REQUEST_URL = "https://api.themoviedb.org/3/movie/";
    private static final int MOVIE_LOADER_ID = 1;

    private RecyclerView mRecyclerView;
    private MovieAdapter mMovieAdapter;
    private TextView mErrorMessageDisplay;
    private ProgressBar mLoadingIndicator;

    private boolean isConnected;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies);


        // Find the reference to the ListView in the layout
        mRecyclerView = (RecyclerView) findViewById(R.id.rv_movie_list);

        // Find the reference to the Empty view layout
        // mErrorMessageDisplay = (TextView) findViewById(R.id.tv_error_view);

        // Creating GridLayout to populate the movies as grid
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        mRecyclerView.setLayoutManager(gridLayoutManager);
        mRecyclerView.setHasFixedSize(true);

        // Create a new adapter that takes an empty list of movies as input
        mMovieAdapter = new MovieAdapter( this);
        mMovieAdapter.setContext(getApplicationContext());
        mMovieAdapter.setMovieList(new ArrayList<Movie>());

        // Set the adapter on the RecyclerView
        // so the list can be populated in the user interface
        mRecyclerView.setAdapter(mMovieAdapter);

        // Initialize the loading indicator
        // mLoadingIndicator = (ProgressBar) findViewById(R.id.pb_loading_indicator);

        populateMovieList();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            Intent settingsIntent = new Intent(this, SettingsActivity.class);
            startActivity(settingsIntent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(Movie movie) {
        Intent intent = new Intent(this, MovieDetailActivity.class);
        intent.putExtra(MOVIE_BUNDLE_KEY, movie);
        startActivity(intent);
    }

    private boolean checkForNetworkStatus() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();

        return activeNetwork != null && activeNetwork.isConnected();
    }

    private void populateMovieList() {

        // Before populating the list, check for the network status
        isConnected = checkForNetworkStatus();
        // If it's connected it will call the load manager otherwise will display no connection message
        if (isConnected) {
            // Start Loader Manager
            getLoaderManager().initLoader(MOVIE_LOADER_ID, null, this);
        }
        else {
            // Update empty state with no connection message
            // mEmptyStateTextView.setText(R.string.no_connection);
        }

    }

    private void resetAdapter() {
        // Create a new adapter with an empty movie list
        mMovieAdapter.setMovieList(new ArrayList<Movie>());
        mRecyclerView.setAdapter(mMovieAdapter);
    }

    private Uri.Builder createUri(String sort_by) {

        String apiKey = getString(R.string.movie_api_key);

        Uri baseUri = Uri.parse(MOVIES_REQUEST_URL);
        Uri.Builder uriBuilder = baseUri.buildUpon();

        uriBuilder.appendPath(sort_by);
        uriBuilder.appendQueryParameter("api_key", apiKey);

        return uriBuilder;
    }

    @Override
    public Loader<List<Movie>> onCreateLoader(int i, Bundle bundle) {

        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);
        String orderBy = sharedPrefs.getString(
                getString(R.string.settings_order_by_key),
                getString(R.string.settings_order_by_default));

        Uri.Builder  uri = createUri(orderBy);

        Log.i(LOG_TAG, uri.toString());

        return new MoviesLoader(this, uri.toString());
    }

    @Override
    public void onLoadFinished(Loader<List<Movie>> loader, List<Movie> movies) {

        // Set empty state text to display "No movies found."
        // mEmptyStateTextView.setText(R.string.no_movies);

        // Clear the adapter from previous data
        resetAdapter();

        // If movies is not empty or null populate the adapter
        if(!movies.isEmpty() && movies != null) {
            Log.i(LOG_TAG, String.valueOf(movies.size()));
            mMovieAdapter.setMovieList(movies);
            mRecyclerView.setAdapter(mMovieAdapter);
        }

    }

    @Override
    public void onLoaderReset(Loader<List<Movie>> loader) {
        resetAdapter();
    }
}
