package com.example.popularmovies.api

import android.content.Context

class MoviesRepository(var context: Context) {
    private val moviesApi: MoviesApiService = MoviesApi.createApi()

    fun getMovies(year: String) = moviesApi.getMoviesByYear(
        mapOf(
            "api_key" to "5e7edcdfa5ef44b1137152b834207944",
            "language" to "en-US",
            "sort_by" to "popularity.desc",
            "include_adult" to "false",
            "include_video" to "false",
            "page" to "1",
            "primary_release_year" to year)
    )
}