package com.iamjoey.heartstonejson.ui.card

import android.os.Bundle
import android.widget.Button
import android.content.Intent
import com.bumptech.glide.Glide
import com.iamjoey.heartstonejson.R
import androidx.lifecycle.ViewModelProvider
import androidx.appcompat.app.AppCompatActivity
import com.iamjoey.heartstonejson.model.CardItem
import com.iamjoey.heartstonejson.ui.main.EXTRA_CARD
import kotlinx.android.synthetic.main.card_detail_activity.*
import com.iamjoey.heartstonejson.ui.main.ADD_CARD_RESULT_CODE
import com.iamjoey.heartstonejson.ui.main.DELETE_CARD_RESULT_CODE

class CardDetailActivity : AppCompatActivity() {

    private lateinit var cardItem: CardItem

    private lateinit var viewModel: CardDetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.card_detail_activity)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        initViews()
        initViewModel()

        val btn = findViewById<Button>(R.id.button)
        val card = CardItem(cardItem.id, cardItem.name, cardItem.text)
        val resultIntent = Intent()

        resultIntent.putExtra(EXTRA_CARD, card)

        if (viewModel.isAddedToDeck(card.id)) {
            btn.text = getString(R.string.remove_card_from_deck_btn)
            btn.setOnClickListener {
                setResult(DELETE_CARD_RESULT_CODE, resultIntent)
                finish()
            }
        } else {
            btn.text = getString(R.string.add_card_to_deck_btn)
            btn.setOnClickListener {
                setResult(ADD_CARD_RESULT_CODE, resultIntent)
                finish()
            }
        }
    }

    private fun initViews() {
        cardItem = intent.extras?.getParcelable(EXTRA_CARD)!!
        Glide.with(this).load(cardItem.getCardImage()).into(ivPoster)
        setTitle(cardItem.name)
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(this).get(CardDetailViewModel::class.java)
    }
}