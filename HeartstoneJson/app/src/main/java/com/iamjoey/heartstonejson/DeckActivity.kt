package com.iamjoey.heartstonejson

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class DeckActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.card_detail_activity)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        initViews()
    }

    private fun initViews() {

    }
}