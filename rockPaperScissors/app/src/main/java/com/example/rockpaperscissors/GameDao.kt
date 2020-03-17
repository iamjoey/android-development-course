package com.example.rockpaperscissors

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

/*
* Data Access Object interface.
* This is used by the game repository class.
 */
@Dao
interface GameDao {
    /**
     * The suspend type means that this method can only run in a coroutine.
     */
    @Query("SELECT * FROM games")
    suspend fun getAllGames():List<Game>

    @Insert
    suspend fun addGame(game: Game)

    @Query("DELETE FROM games")
    suspend fun clearGameHistory()

    @Query("SELECT COUNT(*) FROM games WHERE result = 0")
    suspend fun getLossesCount(): Int

    @Query("SELECT COUNT(*) FROM games WHERE result = 1")
    suspend fun getDrawsCount(): Int

    @Query("SELECT COUNT(*) FROM games WHERE result = 2")
    suspend fun getWinsCount(): Int
}