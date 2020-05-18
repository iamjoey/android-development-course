package com.example.popularmovies.ui.main

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.AndroidViewModel
import com.example.popularmovies.model.MoviePage
import com.example.popularmovies.api.MoviesRepository

class MainActivityViewModel(application: Application) : AndroidViewModel(application) {

    private val moviesRepository = MoviesRepository(application.applicationContext)

    val moviesPage = MutableLiveData<MoviePage>()

    val error = MutableLiveData<String>()

    val progressBarStatus = MutableLiveData<Boolean>(false)

    fun getMovies(year: String) {
        progressBarStatus.value = true

        moviesRepository.getMovies(year).enqueue(object : Callback<MoviePage> {
            override fun onResponse(call: Call<MoviePage>, response: Response<MoviePage>) {
                if (response.isSuccessful) moviesPage.value = response.body()
                else error.value = "An error occurred: ${response.errorBody().toString()}"
                progressBarStatus.value = false
            }

            override fun onFailure(call: Call<MoviePage>, t: Throwable) {
                error.value = t.message
                progressBarStatus.value = false
            }
        })
    }
}