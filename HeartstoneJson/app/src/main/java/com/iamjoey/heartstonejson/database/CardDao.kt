package com.iamjoey.heartstonejson.database

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Delete
import androidx.room.Insert
import androidx.lifecycle.LiveData
import com.iamjoey.heartstonejson.model.Card

@Dao
interface CardDao {
    @Query("SELECT * FROM cardTable")
    fun getCards(): LiveData<List<Card>>

    @Query("SELECT * FROM cardTable where id = :id")
    fun findById(id: String): Card

    @Insert
    suspend fun insertCard(card: Card)

    @Delete
    suspend fun deleteCard(card: Card)

    @Query("DELETE FROM cardTable")
    suspend fun deleteAllCards()
}