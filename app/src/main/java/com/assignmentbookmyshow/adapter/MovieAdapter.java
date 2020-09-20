package com.assignmentbookmyshow.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.recyclerview.widget.RecyclerView;

import com.assignmentbookmyshow.R;
import com.assignmentbookmyshow.data.model.Configuration;
import com.assignmentbookmyshow.data.model.MovieData;
import com.assignmentbookmyshow.ui.viewmodel.ListViewModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {
    private List<MovieData> movies = new ArrayList<>();
    private Context context;
    private ItemClickListener itemClickListener;
    private Configuration configuration;

    public MovieAdapter(Configuration configuration, ItemClickListener itemClickListener) {
        this.configuration = configuration;
        this.itemClickListener = itemClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_movie_list, parent, false);
        return new ViewHolder(itemView, configuration, itemClickListener);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bind(movies.get(position));
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public void clear() {
        movies.clear();
    }

    public void addAll(List<MovieData> movies) {
        this.movies.addAll(movies);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        View itemView;
        ImageView imageView;
        TextView popularityTextView;
        TextView titleTextView;
        private MovieData movieData;
        Configuration configuration;

        ViewHolder(View itemView, Configuration configuration, ItemClickListener itemClickListener) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            this.itemView = itemView;
            this.configuration = configuration;
            itemView.setOnClickListener(v -> {
                if(movieData != null) {
                    itemClickListener.onItemClick(movieData.id);
                }
            });
        }

        void bind(MovieData movieData) {
            this.movieData = movieData;
            String popularity = getPopularityString(movieData.popularity);
            popularityTextView.setText(popularity);
            titleTextView.setText(movieData.title);
            String fullImageUrl = getFullImageUrl(movieData);
            if (!fullImageUrl.isEmpty()) {
                Picasso.get()
                        .load(fullImageUrl)
                        .into(imageView);
            }
        }

        private String getPopularityString(float popularity) {
            java.text.DecimalFormat decimalFormat = new java.text.DecimalFormat("#.#");
            return decimalFormat.format(popularity);
        }

        @NonNull
        private String getFullImageUrl(MovieData movie) {
            String imagePath = "";

            if (movie.posterPath != null && !movie.posterPath.isEmpty()) {
                imagePath = movie.posterPath;
            }

            if (configuration != null && configuration.baseUrl != null && !configuration.baseUrl.isEmpty()) {
                // back-off to hard-coded value
                return configuration.baseUrl + "w500" + imagePath;
            }

            return "";
        }
    }


    public interface ItemClickListener {

        void onItemClick(int movieId);

    }
}
