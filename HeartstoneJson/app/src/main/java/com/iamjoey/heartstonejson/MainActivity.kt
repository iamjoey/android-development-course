package com.iamjoey.heartstonejson

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewTreeObserver
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.iamjoey.heartstonejson.model.Card
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.math.floor
import androidx.lifecycle.Observer

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainActivityViewModel

    private val cards = arrayListOf<Card>()

    private val cardAdapter = CardAdapter(cards) {}

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

        rvMovies.layoutManager = gridLayoutManager

        // Global Layout Listener which will calculate the span count.
        rvMovies.viewTreeObserver.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                rvMovies.viewTreeObserver.removeOnGlobalLayoutListener(this)
                gridLayoutManager.spanCount = calculateSpanCount()
                gridLayoutManager.requestLayout()
            }
        })

        rvMovies.adapter = cardAdapter
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)

        viewModel.cardsPage.observe(this, Observer {
            cards.clear()
            cards.addAll(it.cards)
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

    /**
     * Calculate the number of spans for the recycler view based on the recycler view width.
     * @return int number of spans.
     */
    private fun calculateSpanCount(): Int {
        val viewWidth = rvMovies.measuredWidth
        val cardViewWidth = resources.getDimension(R.dimen.poster_width)
        val cardViewMargin = resources.getDimension(R.dimen.margin_medium)
        val spanCount = floor((viewWidth / (cardViewWidth + cardViewMargin)).toDouble()).toInt()
        return if (spanCount >= 1) spanCount else 1
    }
}
