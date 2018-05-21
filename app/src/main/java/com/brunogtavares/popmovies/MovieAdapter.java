package com.brunogtavares.popmovies;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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
 * Read about parcels: http://www.vogella.com/tutorials/AndroidParcelable/article.html
 */

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {

    private static final String LOG_TAG = MovieAdapter.class.getSimpleName();

    private List<Movie> mMovieList;
    private final Context mContext;
    private final ListItemClickListener mOnclickListener;

    public interface ListItemClickListener {
        void onListItemClick(Movie movieItem);
    }

    public MovieAdapter(Context context, List<Movie> movieList)  {
        this.mMovieList = movieList;
        this.mContext = context;
    }

    public class MovieViewHolder extends RecyclerView.ViewHolder
        implements View.OnClickListener {
        public ImageView mMovieImageView;

        public MovieViewHolder(View itemView) {
            super(itemView);
            mMovieImageView = (ImageView) itemView.findViewById(R.id.iv_movie_poster);
            itemView.setOnClickListener(this);
        }


        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            // mOnclickListener.onListItemClick(mMovieList.get(position));
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

        Log.d(LOG_TAG, "We are here: # " + position + "\n" +
            movie.getPoster() + "\n" +
            movie.toString());


        Picasso.with(mContext)
                .load(movie.getPoster())
                .into(holder.mMovieImageView);
    }

    @Override
    public int getItemCount() {
        if(mMovieList.isEmpty() || mMovieList == null) return 0;
        return mMovieList.size();
    }
}
