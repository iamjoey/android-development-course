package com.example.rockpaperscissors

import android.content.Context

//responsible for using the DAO to make operations on database
//instead of directly working with DAOs in activity classes, we will simply create a repository
//and work through its methods

// This is the class that is being used to talk to the database.
// Instead of using the DAO in the activity class we use the repositry class.
// The repository then reflects the queries that are defined in the dao class.
class GameRepository(context: Context) {
    private val gameDao: GameDao

    init {
        val database = GameHistoryRoomDatabase.getDatabase(context)
        gameDao = database!!.gameDao() // !! helps asserting that the database is not null https://kotlinlang.org/docs/reference/null-safety.html#the--operator
    }

    suspend fun getAllGames(): List<Game> = gameDao.getAllGames()

    suspend fun addGame(game: Game) = gameDao.addGame(game)

    suspend fun clearGameHistory() = gameDao.clearGameHistory()

    suspend fun getLossesCount(): Int = gameDao.getLossesCount()

    suspend fun getDrawsCount(): Int = gameDao.getDrawsCount()

    suspend fun getWinsCount(): Int = gameDao.getWinsCount()
}