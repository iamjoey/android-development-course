package com.iamjoey.heartstonejson.ui.deck

import android.os.Bundle
import android.view.Menu
import android.widget.Toast
import android.view.MenuItem
import androidx.lifecycle.Observer
import com.iamjoey.heartstonejson.R
import androidx.lifecycle.ViewModelProvider
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.iamjoey.heartstonejson.model.CardItem
import androidx.recyclerview.widget.ItemTouchHelper
import kotlinx.android.synthetic.main.deck_activity.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.DividerItemDecoration

class DeckActivity: AppCompatActivity() {

    private val cards = arrayListOf<CardItem>()

    private val deckCardAdapter = DeckCardAdapter(cards)

    private lateinit var viewModel: DeckActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.deck_activity)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        initViews()
        initViewModel()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.deck_menu, menu)
        return true
    }

    private fun initViews() {
        rvCards.layoutManager = LinearLayoutManager(this@DeckActivity, RecyclerView.VERTICAL, false)
        rvCards.adapter = deckCardAdapter
        rvCards.addItemDecoration(DividerItemDecoration(this@DeckActivity, DividerItemDecoration.VERTICAL))

        createItemTouchHelper().attachToRecyclerView(rvCards)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_delete -> {
                viewModel.deleteAllCards()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(this).get(DeckActivityViewModel::class.java)
        getDeckFromDatabase()
    }

    private fun createItemTouchHelper(): ItemTouchHelper {
        val callback = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {

            // Disables the ability to move items up and down.
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            // Callback triggered when a user swiped an item.
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                val cardToDelete = cards[position]
                viewModel.deleteCard(cardToDelete)
                Toast.makeText(applicationContext, getString(R.string.card_removed_toast), Toast.LENGTH_SHORT).show()
                getDeckFromDatabase()
            }
        }

        return ItemTouchHelper(callback)
    }

    private fun getDeckFromDatabase() {
        viewModel.cards.observe(this, Observer {
                cards ->
            this@DeckActivity.cards.clear()
            this@DeckActivity.cards.addAll(cards)
            this@DeckActivity.deckCardAdapter.notifyDataSetChanged()
        })
    }

}