package com.iamjoey.heartstonejson.api

import retrofit2.Retrofit
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.converter.gson.GsonConverterFactory

class HeartstoneApi {
    companion object {
        private const val baseUrl = "https://api.hearthstonejson.com/"

        fun createApi(): HeartstoneApiService {
            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .build()

            val api = Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            return api.create(HeartstoneApiService::class.java)
        }
    }
}