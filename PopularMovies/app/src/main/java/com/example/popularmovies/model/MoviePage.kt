package com.example.popularmovies.model

import com.google.gson.annotations.SerializedName

data class MoviePage(
    @SerializedName("results") var movies: List<Movie>
)