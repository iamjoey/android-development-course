package com.example.numbertrivia.api

import com.example.numbertrivia.model.Trivia
import retrofit2.Call
import retrofit2.http.GET

public interface NumbersApiService {

    @GET("/random/trivia?json")
    fun getRandomNumberTrivia(): Call<Trivia>
}