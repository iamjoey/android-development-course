package com.iamjoey.heartstonejson.ui.card

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.graphics.Color
import android.widget.Button
import android.widget.Toast
import com.bumptech.glide.Glide
import com.iamjoey.heartstonejson.R
import com.iamjoey.heartstonejson.model.Card
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.appbar.AppBarLayout
import com.iamjoey.heartstonejson.ui.deck.DeckActivity
import com.iamjoey.heartstonejson.ui.deck.DeckActivityViewModel
import com.iamjoey.heartstonejson.ui.main.EXTRA_CARD
import kotlinx.android.synthetic.main.card_detail_activity.*

class CardDetailActivity : AppCompatActivity() {

    private lateinit var card: Card

    private lateinit var viewModel: CardDetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.card_detail_activity)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        initViews()
        initViewModel()

        val btn = findViewById<Button>(R.id.button)

        if (viewModel.isAddedToDeck(card.id)) {
            btn.text = "Add to deck"
            btn.setOnClickListener {
                val card = Card(card.name, card.id)
                val resultIntent = Intent()
                resultIntent.putExtra(EXTRA_CARD, card)
                setResult(Activity.RESULT_OK, resultIntent)
                finish()
            }
        } else {
            btn.text = "Remove from deck"
        }
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(this).get(CardDetailViewModel::class.java)
    }

    private fun initViews() {
        card = intent.extras?.getParcelable("EXTRA_CARD")!!
        Glide.with(this).load(card.getCardUrl()).into(ivPoster)
    }
}