package com.example.rockpaperscissors

import android.view.Menu
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.MenuItem
import kotlinx.android.synthetic.main.game_history_activity.*
import androidx.recyclerview.widget.DividerItemDecoration
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.withContext
import kotlinx.android.synthetic.main.game_history_content.*
import kotlinx.coroutines.launch
import android.os.Bundle
import kotlinx.coroutines.Dispatchers

class GameHistoryActivity : AppCompatActivity() {

    private val games = arrayListOf<Game>()

    private val gameAdapter = GameAdaptre(games)

    private lateinit var gameRepository: GameRepository

    private val mainScope = CoroutineScope(Dispatchers.Main)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.game_history_activity)
        setSupportActionBar(toolbar)
        gameRepository = GameRepository(this)
        initViews()
    }

    private fun initViews() {
        rvHistory.layoutManager = LinearLayoutManager(this@GameHistoryActivity, RecyclerView.VERTICAL, false)
        rvHistory.addItemDecoration(DividerItemDecoration(this@GameHistoryActivity, DividerItemDecoration.VERTICAL))
        rvHistory.adapter = gameAdapter
        getGameHistory()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // inflate the menu to convert it to a thing android understands
        // this will add add items to the action bar if they are present
        menuInflater.inflate(R.menu.menu_history, menu) // https://developer.android.com/guide/topics/ui/menus
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle the action bar item click here which in this case is clearing the game history
        // in the AndroidManifest.xml the parent is specified
        // this will automatically handle clicks on the back button
        return when (item.itemId) {
            R.id.action_clear -> {
                clearGameHistory()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun getGameHistory() {
        mainScope.launch {
            val gameList = withContext(Dispatchers.IO) {
                gameRepository.getAllGames()
            }
            this@GameHistoryActivity.games.clear()
            this@GameHistoryActivity.games.addAll(gameList)
            this@GameHistoryActivity.games.reverse()
            this@GameHistoryActivity.gameAdapter.notifyDataSetChanged()
        }
    }

    private fun clearGameHistory() {
        mainScope.launch {
            withContext(Dispatchers.IO) {
                gameRepository.clearGameHistory()
            }
            getGameHistory()
            Toast.makeText(this@GameHistoryActivity, "Your game history is deleted", Toast.LENGTH_SHORT).show()
        }
    }

}