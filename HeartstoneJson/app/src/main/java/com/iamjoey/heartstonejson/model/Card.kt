package com.iamjoey.heartstonejson.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
import com.google.gson.annotations.SerializedName

@Parcelize
@Entity(tableName = "cardTable")
data class Card(

    @ColumnInfo(name = "name")
    @SerializedName("name")
    var name: String,

    @ColumnInfo(name = "id")
    @SerializedName("id")
    @PrimaryKey(autoGenerate = false)
    var id: String

) :Parcelable {
    fun getCardUrl() = "https://art.hearthstonejson.com/v1/render/latest/enUS/512x/$id.png"
}