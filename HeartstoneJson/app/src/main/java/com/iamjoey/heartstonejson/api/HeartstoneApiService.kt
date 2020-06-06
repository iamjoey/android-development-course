package com.iamjoey.heartstonejson.api

import retrofit2.Call
import retrofit2.http.GET
import com.iamjoey.heartstonejson.model.Card

public interface HeartstoneApiService {
    @GET("/v1/25770/enUS/cards.collectible.json")
    fun getAllCards(): Call<Card>
}