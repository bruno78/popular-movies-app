package com.brunogtavares.popmovies;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.brunogtavares.popmovies.model.Movie;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by brunogtavares on 5/11/18.
 */

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieAdapterViewHolder> {

    private Context mContext;
    private ArrayList<Movie> mMovies;

    public MovieAdapter(Context context, ArrayList movies) {
        this.mContext = context;
        this.mMovies = movies;
    }

    public class MovieAdapterViewHolder extends RecyclerView.ViewHolder {

        private final ImageView mMoviePoster;

        public MovieAdapterViewHolder(View itemView) {
            super(itemView);
            mMoviePoster = itemView.findViewById(R.id.iv_movie_poster);
        }
    }


    @Override
    public MovieAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_poster_layout,
                parent, false);
        return new MovieAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MovieAdapterViewHolder holder, int position) {

        // Get picture from API and and load into the viewHolder using Picasso
        String movieUrl = mMovies.get(position).getPoster();
        Picasso.with(mContext)
                .load(movieUrl)
                .into(holder.mMoviePoster);

        // Set OnclickListener to the mMoviePoster
        holder.mMoviePoster.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, MovieDetailActivity.class);
                // Passing the image of the clicked movie poster to the detail activity
                intent.putExtra("moviePoster", mMovies.get(holder.getAdapterPosition()).getPoster());
                // Start the activity
                mContext.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        if(mMovies == null) return 0;
        return mMovies.size();
    }

}
