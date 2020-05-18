package com.example.popularmovies.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import com.google.gson.annotations.SerializedName

@Parcelize
data class Movie(
    @SerializedName("title") var title: String,
    @SerializedName("overview") var overview: String,
    @SerializedName("vote_average") var rating: Float,
    @SerializedName("poster_path") var posterImage: String,
    @SerializedName("release_date") var releaseDate: String,
    @SerializedName("backdrop_path") var backdropImage: String
) :Parcelable {
    fun getPosterUrl() = "https://image.tmdb.org/t/p/w185/$posterImage"
    fun getBackdropUrl() = "https://image.tmdb.org/t/p/w500/$backdropImage"
}