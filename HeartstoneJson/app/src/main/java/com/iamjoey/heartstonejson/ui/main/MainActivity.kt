package com.iamjoey.heartstonejson.ui.main

import kotlin.math.floor
import android.view.View
import android.os.Bundle
import android.widget.Toast
import android.content.Intent
import androidx.lifecycle.Observer
import com.iamjoey.heartstonejson.R
import android.view.ViewTreeObserver
import androidx.lifecycle.ViewModelProvider
import com.iamjoey.heartstonejson.model.Card
import androidx.appcompat.app.AppCompatActivity
import com.iamjoey.heartstonejson.model.CardItem
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import androidx.recyclerview.widget.GridLayoutManager
import com.iamjoey.heartstonejson.ui.deck.DeckActivity
import com.iamjoey.heartstonejson.ui.card.CardDetailActivity

const val EXTRA_CARD = "EXTRA_CARD"

const val ADD_CARD_RESULT_CODE = 200

const val DELETE_CARD_RESULT_CODE = 300

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: MainActivityViewModel

    private val cards = Card()

    private val cardAdapter = CardAdapter(cards) { cardItem -> onCardClick(cardItem) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViews()
        initViewModel()

        viewModel.getCards()
    }

    private fun initViews() {
        val gridLayoutManager = GridLayoutManager(
            this,
            1,
            RecyclerView.VERTICAL,
            false
        )

        rvCards.layoutManager = gridLayoutManager

        // Global Layout Listener which will calculate the span count.
        rvCards.viewTreeObserver.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                rvCards.viewTreeObserver.removeOnGlobalLayoutListener(this)
                gridLayoutManager.spanCount = calculateSpanCount()
                gridLayoutManager.requestLayout()
            }
        })

        rvCards.adapter = cardAdapter

        val fab: View = findViewById(R.id.fab)
        fab.setOnClickListener {
            val intent = Intent(this, DeckActivity::class.java)
            startActivityForResult(intent, 100)
        }
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)

        viewModel.cardsPage.observe(this, Observer {
            cards.clear()
            cards.addAll(it)
            cardAdapter.notifyDataSetChanged()
        })

        viewModel.error.observe(this, Observer {
            Toast.makeText(this, it, Toast.LENGTH_LONG).show()
        })

        viewModel.progressBarStatus.observe(this, Observer {
            if (it) progressBar.visibility = View.VISIBLE
            else progressBar.visibility = View.GONE
        })
    }

    private fun onCardClick(cardItem: CardItem) {
        val intent = Intent(this, CardDetailActivity::class.java)
        intent.putExtra(EXTRA_CARD, cardItem)
        startActivityForResult(intent, 100)
    }

    /**
     * Calculate the number of spans for the recycler view based on the recycler view width.
     * @return int number of spans.
     */
    private fun calculateSpanCount(): Int {
        val viewWidth = rvCards.measuredWidth
        val cardViewWidth = resources.getDimension(R.dimen.poster_width)
        val cardViewMargin = resources.getDimension(R.dimen.margin_medium)
        val spanCount = floor((viewWidth / (cardViewWidth + cardViewMargin)).toDouble()).toInt()
        return if (spanCount >= 1) spanCount else 1
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == ADD_CARD_RESULT_CODE) {
            val card = data!!.getParcelableExtra<CardItem>(EXTRA_CARD)
            viewModel.insertCard(card)
            Toast.makeText(applicationContext, getString(R.string.card_added_toast), Toast.LENGTH_SHORT).show()
        }

        if (resultCode == DELETE_CARD_RESULT_CODE) {
            val card = data!!.getParcelableExtra<CardItem>(EXTRA_CARD)
            viewModel.deleteCard(card)
            Toast.makeText(applicationContext, getString(R.string.card_removed_toast), Toast.LENGTH_SHORT).show()
        }

        super.onActivityResult(requestCode, resultCode, data)
    }

}