package com.assignmentbookmyshow;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.assignmentbookmyshow.adapter.MovieAdapter;
import com.assignmentbookmyshow.data.model.Configuration;
import com.assignmentbookmyshow.data.model.MovieData;
import com.assignmentbookmyshow.ui.viewmodel.ListViewModel;

import java.util.ArrayList;

import butterknife.BindView;

public class MovieListActivity extends AppCompatActivity implements LifecycleOwner,MovieAdapter.ItemClickListener {
    RecyclerView recyclerView;
    private ListViewModel viewModel;
    private Configuration configuration;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_list);

        recyclerView = findViewById(R.id. rv_movie_list);
        viewModel = ViewModelProviders.of(this).get(ListViewModel.class);
        viewModel.getRepos().observe(this,
                userListUpdateObserver);
    }

    Observer<ArrayList<MovieData>> userListUpdateObserver = new Observer<ArrayList<MovieData>>() {
        @Override
        public void onChanged(ArrayList<MovieData> movieData) {
            recyclerView.setAdapter(new MovieAdapter(configuration, MovieListActivity.this));
            recyclerView.setLayoutManager(new LinearLayoutManager(MovieListActivity.this));
        }
    };

    @Override
    public void onItemClick(int movieId) {

    }
}