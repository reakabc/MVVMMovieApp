package com.reakabc.mvvmmovieapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.reakabc.mvvmmovieapp.Adapter.MovieListAdapter;
import com.reakabc.mvvmmovieapp.Model.Movie;
import com.reakabc.mvvmmovieapp.ViewModel.MainViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements MovieListAdapter.ItemClickListener {

    RecyclerView recyclerView;
    MovieListAdapter adapter;
    TextView noItem;
    List<Movie> list;

    MainViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        noItem = findViewById(R.id.tvNoItem);
        recyclerView = findViewById(R.id.recyclerView);

        list = new ArrayList<>();

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new MovieListAdapter(this, list, this);
        recyclerView.setAdapter(adapter);

        viewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        viewModel.getMovieList().observe(this, new Observer<List<Movie>>() {
            @Override
            public void onChanged(List<Movie> movies) {
                if (movies != null){
                    list = movies;
                    adapter.setMovieList(list);
                    noItem.setVisibility(View.GONE);
                }else{
                    noItem.setVisibility(View.VISIBLE);
                }
            }
        });

        viewModel.loadMovies();

    }


    @Override
    public void onItemClick(Movie movie) {
        Toast.makeText(this, movie.getImage(), Toast.LENGTH_SHORT).show();
    }
}