package com.iamjoey.heartstonejson.database

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Delete
import androidx.room.Insert
import androidx.lifecycle.LiveData
import com.iamjoey.heartstonejson.model.CardItem

@Dao
interface CardDao {
    @Query("SELECT * FROM cards")
    fun getCards(): LiveData<List<CardItem>>

    @Query("SELECT * FROM cards where id = :id")
    fun findById(id: String): CardItem

    @Insert
    suspend fun insertCard(cardItem: CardItem)

    @Delete
    suspend fun deleteCard(cardItem: CardItem)

    @Query("DELETE FROM cards")
    suspend fun deleteAllCards()
}