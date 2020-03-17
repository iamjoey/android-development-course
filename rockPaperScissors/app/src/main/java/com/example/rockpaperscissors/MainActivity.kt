package com.example.rockpaperscissors


import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.main_activity.*
import kotlinx.android.synthetic.main.main_content.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var gameRepository: GameRepository

    private val mainScope = CoroutineScope(Dispatchers.Main)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        setSupportActionBar(toolbar)

        gameRepository = GameRepository(this)

        updateStatistics()

        ivRock.setOnClickListener { playOneRound(0) }
        ivPaper.setOnClickListener { playOneRound(1) }
        ivScissors.setOnClickListener { playOneRound(2) }

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // inflate the menu to convert it to a thing android understands
        // this will add add items to the action bar if they are present
        menuInflater.inflate(R.menu.menu_main, menu) // https://developer.android.com/guide/topics/ui/menus
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle the action bar item click here which in this case is clearing the game history
        // in the AndroidManifest.xml the parent is specified
        // this will automatically handle clicks on the back button
        return when (item.itemId) {
            R.id.action_history -> {
                startHistoryActivity()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun playOneRound(playersChoice: Int) {
        val rock = 0;
        val paper = 1;
        val scissors = 2;

        val lose = 0;
        val draw = 1;
        val win = 2;

        val computersChoice = (0..2).random() // computer's answer
        var result: Int = -1

        ivComputer.setImageResource(Game.GAME_RES_DRAWABLES_IDS[computersChoice])
        ivYou.setImageResource(Game.GAME_RES_DRAWABLES_IDS[playersChoice])

        if (playersChoice == computersChoice) {
            result = lose
        } else {
            when (playersChoice) {
                rock -> {
                    when (computersChoice) {
                        paper -> result = lose
                        scissors -> result = win
                    }
                }
                paper -> {
                    when (computersChoice) {
                        rock -> result = win
                        scissors -> result = lose
                    }
                }
                scissors -> {
                    when (computersChoice) {
                        rock -> result = lose
                        paper -> result = win
                    }
                }
            }
        }

        when(result) {
            lose -> tvResult.text = getString(R.string.computer_wins)
            draw -> tvResult.text = getString(R.string.draw)
            win -> tvResult.text = getString(R.string.you_win)
        }

        mainScope.launch {
            val game = Game(date = Date(), computer = computersChoice,
                player = playersChoice, result = result)
            withContext(Dispatchers.IO) {
                gameRepository.addGame(game)
            }

            updateStatistics()
        }

    }

    private fun startHistoryActivity() {
        val intent = Intent(this, GameHistoryActivity::class.java)
        startActivity(intent)
    }

    private fun updateStatistics() {
        mainScope.launch {
            val stats = withContext(Dispatchers.IO) {
                return@withContext arrayOf(gameRepository.getLossesCount(),
                    gameRepository.getDrawsCount(), gameRepository.getWinsCount())
            }
            this@MainActivity.tvStatistics.text =
                "LOST: " + stats[0] + "Draws: " + stats[1] + "Wins: " + stats[2]

        }
    }
}