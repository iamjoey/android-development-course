package com.example.gamebacklog.ui.main

import android.os.Bundle
import android.view.Menu
import android.app.Activity
import android.view.MenuItem
import android.content.Intent
import com.example.gamebacklog.R
import androidx.lifecycle.Observer
import com.example.gamebacklog.model.Game
import androidx.lifecycle.ViewModelProvider
import androidx.appcompat.app.AppCompatActivity
import com.example.gamebacklog.ui.add.GAME_VIEW
import com.example.gamebacklog.ui.add.AddActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar

import kotlinx.android.synthetic.main.content_main.*
import kotlinx.android.synthetic.main.activity_main.*

const val ADD_GAME_REQUEST_CODE = 100

class MainActivity : AppCompatActivity() {
    private val games = arrayListOf<Game>()
    private val gameAdapter = GameAdapter(games)
    private lateinit var viewModel: MainActivityViewModel
    private val savedGamesList = arrayListOf<Game>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        initViews()
        initViewModel()

        fab.setOnClickListener {
            startAddActivity()
        }
    }

    private fun startAddActivity() {
        val intent = Intent(this, AddActivity::class.java)
        startActivityForResult(intent, ADD_GAME_REQUEST_CODE)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu) // add items to action bar if present.
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_delete -> {
                savedGamesList.clear()
                savedGamesList.addAll(games)
                viewModel.deleteAllGames()
                Snackbar.make(coordinatorLayout, "Successfully deleted backlog", Snackbar.LENGTH_LONG)
                    .setAction("UNDO") {
                        savedGamesList.forEach {
                            viewModel.insertGame(it)
                        }
                    }
                    .show()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun initViews() {
        rvGames.layoutManager = LinearLayoutManager(this@MainActivity, RecyclerView.VERTICAL, false)
        rvGames.adapter = gameAdapter
        createItemTouchHelper().attachToRecyclerView(rvGames)
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)

        viewModel.games.observe(this, Observer {
                games ->
            this@MainActivity.games.clear()
            this@MainActivity.games.addAll(games)
            gameAdapter.notifyDataSetChanged()
        })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == Activity.RESULT_OK) {
            when (requestCode) {
                ADD_GAME_REQUEST_CODE -> {
                    val game = data!!.getParcelableExtra<Game>(GAME_VIEW)
                    viewModel.insertGame(game)
                }
                else -> super.onActivityResult(requestCode, resultCode, data)
            }
        }
    }

    // Enables user swipes to delete a game from the recycler view.
    // Uses a callback to signal when a user is make this action.
    private fun createItemTouchHelper(): ItemTouchHelper {

        val callback = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {

            // Enable or disables the ability to move items up and down.
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            // Callback triggered when a user swiped the item.
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                val gameToDelete = games[position]
                viewModel.deleteGame(gameToDelete)
                Snackbar.make(coordinatorLayout, "Successfully deleted game", Snackbar.LENGTH_LONG)
                    .setAction("UNDO") {
                        viewModel.insertGame(gameToDelete)
                    }
                    .show()
            }
        }
        return ItemTouchHelper(callback)
    }
}