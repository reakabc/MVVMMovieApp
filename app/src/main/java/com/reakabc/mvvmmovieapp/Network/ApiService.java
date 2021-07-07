package com.reakabc.mvvmmovieapp.Network;

import com.reakabc.mvvmmovieapp.Model.Movie;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {

    @GET("") //enter left part here of url
    Call<List<Movie>> getMovies();

}
