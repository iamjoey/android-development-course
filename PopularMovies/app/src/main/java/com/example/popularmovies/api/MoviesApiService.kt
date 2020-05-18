package com.example.popularmovies.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.QueryMap
import com.example.popularmovies.model.MoviePage

public interface MoviesApiService {
    @GET("/3/discover/movie")
    fun getMoviesByYear(@QueryMap params: Map<String, String>): Call<MoviePage>
}