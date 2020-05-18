package com.iamjoey.heartstonejson.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import com.google.gson.annotations.SerializedName

@Parcelize
data class Card(
    @SerializedName("id") var id: String,
    @SerializedName("name") var name: String
) :Parcelable {
    fun getCardUrl() = "https://art.hearthstonejson.com/v1/render/latest/enUS/512x/$id.png"
}