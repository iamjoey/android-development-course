package com.iamjoey.heartstonejson.model

import androidx.room.Entity
import android.os.Parcelable
import androidx.room.PrimaryKey
import androidx.room.ColumnInfo
import kotlinx.android.parcel.Parcelize
import com.google.gson.annotations.SerializedName

class Card : ArrayList<CardItem>()

@Parcelize
@Entity(tableName = "cards")
data class CardItem(
    @ColumnInfo(name = "id")
    @SerializedName("id")
    @PrimaryKey(autoGenerate = false)
    val id: String,

    @SerializedName("name")
    @ColumnInfo(name = "name")
    val name: String,

    @SerializedName("text")
    @ColumnInfo(name = "text")
    val text: String

) : Parcelable {
    fun getCardTile() = "https://art.hearthstonejson.com/v1/tiles/$id.jpg"
    fun getCardImage() = "https://art.hearthstonejson.com/v1/render/latest/enUS/512x/$id.png"
}