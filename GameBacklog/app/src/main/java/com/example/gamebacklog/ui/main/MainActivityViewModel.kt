package com.example.gamebacklog.ui.main

import android.app.Application
import kotlinx.coroutines.launch
import androidx.lifecycle.LiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.CoroutineScope
import com.example.gamebacklog.model.Game
import androidx.lifecycle.AndroidViewModel
import com.example.gamebacklog.database.GameRepository

class MainActivityViewModel(application: Application) : AndroidViewModel(application) {
    private val gameRepository = GameRepository(application.applicationContext)
    private val ioScope = CoroutineScope(Dispatchers.IO)

    val games: LiveData<List<Game>> = gameRepository.getGames()

    fun insertGame(game: Game) {
        ioScope.launch {
            gameRepository.insertGame(game)
        }
    }

    fun deleteGame(game: Game) {
        ioScope.launch {
            gameRepository.deleteGame(game)
        }
    }

    fun deleteAllGames() {
        ioScope.launch {
            gameRepository.deleteAllGames()
        }
    }
}