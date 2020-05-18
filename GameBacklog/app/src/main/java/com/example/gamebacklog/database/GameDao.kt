package com.example.gamebacklog.database

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Delete
import androidx.room.Insert
import androidx.lifecycle.LiveData
import com.example.gamebacklog.model.Game

@Dao
interface GameDao {
    @Query("SELECT * FROM gameTable ORDER BY releaseDate")
    fun getGames(): LiveData<List<Game>>

    @Insert
    suspend fun insertGame(game: Game)

    @Delete
    suspend fun deleteGame(game: Game)

    @Query("DELETE FROM gameTable")
    suspend fun deleteAllGames()
}