package com.iamjoey.heartstonejson

import android.os.Bundle
import android.graphics.Color
import com.bumptech.glide.Glide
import com.iamjoey.heartstonejson.R
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.appbar.AppBarLayout
import com.iamjoey.heartstonejson.model.Card
import kotlinx.android.synthetic.main.card_item.*

class CardDetailActivity : AppCompatActivity() {

    private lateinit var card: Card

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.card_detail)

        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        initViews()
    }

    private fun initViews() {
        card = intent.extras?.getParcelable("CARD")!!

        tvName.text = card.name
        Glide.with(this).load(card.getCardUrl()).into(ivPoster)

        var isShow = true
        var scrollRange = -1
        appbar.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { barLayout, verticalOffset ->
            if (scrollRange == -1){
                scrollRange = barLayout?.totalScrollRange!!
            }
            if (scrollRange + verticalOffset == 0){
                collapsingToolbarLayout.title = card.title
                collapsingToolbarLayout.setCollapsedTitleTextColor(Color.parseColor("#FFFFFFFF"))
                isShow = true
            } else if (isShow){
                collapsingToolbarLayout.title = " " // should have a space.
                isShow = false
            }
        })

    }
}