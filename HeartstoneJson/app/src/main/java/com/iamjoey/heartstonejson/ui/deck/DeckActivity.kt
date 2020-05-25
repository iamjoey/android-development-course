package com.iamjoey.heartstonejson.ui.deck

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import com.iamjoey.heartstonejson.R
import com.iamjoey.heartstonejson.model.Card
import kotlinx.android.synthetic.main.deck_activity.*


class DeckActivity: AppCompatActivity() {

    private val cards = arrayListOf<Card>()

    private val deckCardAdapter = DeckCardAdapter(cards)

    private lateinit var viewModel: DeckActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.deck_activity)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        initViews()
        initViewModel()
    }

    private fun initViews() {
        rvCards.layoutManager = LinearLayoutManager(this@DeckActivity, RecyclerView.VERTICAL, false)
        rvCards.adapter = deckCardAdapter
        rvCards.addItemDecoration(DividerItemDecoration(this@DeckActivity, DividerItemDecoration.VERTICAL))
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(this).get(DeckActivityViewModel::class.java)

        viewModel.cards.observe(this, Observer {
                cards ->
            this@DeckActivity.cards.clear()
            this@DeckActivity.cards.addAll(cards)
        })
    }

}