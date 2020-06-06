package com.iamjoey.heartstonejson.api

import android.content.Context

class HeartstoneRepository(var context: Context) {
    private val heartstoneApi: HeartstoneApiService = HeartstoneApi.createApi()

    fun getCards() = heartstoneApi.getAllCards()
}