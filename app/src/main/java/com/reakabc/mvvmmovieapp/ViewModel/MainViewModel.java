package com.reakabc.mvvmmovieapp.ViewModel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.reakabc.mvvmmovieapp.Model.Movie;
import com.reakabc.mvvmmovieapp.Network.ApiClient;
import com.reakabc.mvvmmovieapp.Network.ApiService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainViewModel extends ViewModel {

    private MutableLiveData<List<Movie>> movieList;

    public MainViewModel() {
        this.movieList = new MutableLiveData<>();
    }

    public MutableLiveData<List<Movie>> getMovieList() {
        return movieList;
    }

    public void loadMovies() {
        ApiService apiService = ApiClient.getRetrofitClient().create(ApiService.class);
        Call<List<Movie>> call = apiService.getMovies();
        call.enqueue(new Callback<List<Movie>>() {
            @Override
            public void onResponse(Call<List<Movie>> call, Response<List<Movie>> response) {
                movieList.postValue(response.body());
            }

            @Override
            public void onFailure(Call<List<Movie>> call, Throwable t) {
                movieList.postValue(null);
            }
        });
    }

}
