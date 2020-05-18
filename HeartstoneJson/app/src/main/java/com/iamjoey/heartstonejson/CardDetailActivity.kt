package com.iamjoey.heartstonejson

import android.os.Bundle
import android.graphics.Color
import com.bumptech.glide.Glide
import com.iamjoey.heartstonejson.R
import com.iamjoey.heartstonejson.model.Card
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.appbar.AppBarLayout
import kotlinx.android.synthetic.main.card_detail_activity.*

class CardDetailActivity : AppCompatActivity() {
    private lateinit var card: Card

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.card_detail_activity)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        initViews()
    }

    private fun initViews() {
        card = intent.extras?.getParcelable("EXTRA_CARD")!!
        Glide.with(this).load(card.getCardUrl()).into(ivPoster)
    }
}