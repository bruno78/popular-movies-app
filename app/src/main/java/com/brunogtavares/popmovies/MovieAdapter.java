package com.brunogtavares.popmovies;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.brunogtavares.popmovies.model.Movie;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by brunogtavares on 5/19/18.
 */

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {

    private static final String LOG_TAG = MovieAdapter.class.getSimpleName();

    private List<Movie> mMovieList;
    private final Context mContext;

    public MovieAdapter(Context context, List<Movie> movieList) {
        this.mMovieList = movieList;
        this.mContext = context;
    }

    public class MovieViewHolder extends RecyclerView.ViewHolder {
        public ImageView mMovieImageView;

        public MovieViewHolder(View view) {
            super(view);
            mMovieImageView = (ImageView) view.findViewById(R.id.iv_movie_poster);
        }
    }

    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.movie_list_item, parent, false);

        return new MovieViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MovieViewHolder holder, int position) {
        Movie movie = mMovieList.get(position);

        Picasso.with(mContext)
                .load(movie.getPoster())
                .into(holder.mMovieImageView);
    }

    @Override
    public int getItemCount() {
        if(mMovieList.isEmpty() || mMovieList == null) return 0;
        return mMovieList.size();
    }



//    @Override
//    public View getView(int position, View convertView, ViewGroup parent) {
//
//        // Check if the existing view is being reused, otherwise inflate the view
//        View listItemView = convertView;
//        if(listItemView == null) {
//            listItemView = LayoutInflater.from(getContext()).inflate(
//                    R.layout.movie_list_item, parent, false );
//        }
//
//        // Get the Movie object located at this position in the list
//        Movie currentMovieItem = getItem(position);
//
//        // Find the ImageView in the movie_list_item.xml
//        ImageView moviePosterImageView = (ImageView) listItemView.findViewById(R.id.iv_movie_poster);
//
//        // Load the image into Image view
//        Picasso.with(mContext)
//                .load(currentMovieItem.getPoster())
//                .into(moviePosterImageView);
//
//        return listItemView;
//    }
}
