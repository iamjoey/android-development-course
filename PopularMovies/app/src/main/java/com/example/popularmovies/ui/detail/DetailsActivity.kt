package com.example.popularmovies.ui.detail

import android.os.Bundle
import android.graphics.Color
import com.bumptech.glide.Glide
import com.example.popularmovies.R
import com.example.popularmovies.model.Movie
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.appbar.AppBarLayout
import kotlinx.android.synthetic.main.activity_details.*

class DetailsActivity : AppCompatActivity() {
    private lateinit var movie: Movie

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        initViews()
    }

    private fun initViews() {
        movie = intent.extras?.getParcelable("EXTRA_MOVIE")!!

        tvTitle.text = movie.title
        Glide.with(this).load(movie.getPosterUrl()).into(ivPoster)
        Glide.with(this).load(movie.getBackdropUrl()).into(ivBackdrop)

        tvDescription.text = movie.overview
        tvRating.text = movie.rating.toString()
        tvReleaseDate.text = "Release: ${movie.releaseDate}"

        var isShow = true
        var scrollRange = -1
        appbar.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { barLayout, verticalOffset ->
            if (scrollRange == -1){
                scrollRange = barLayout?.totalScrollRange!!
            }
            if (scrollRange + verticalOffset == 0){
                collapsingToolbarLayout.title = movie.title
                collapsingToolbarLayout.setCollapsedTitleTextColor(Color.parseColor("#FFF"))
                isShow = true
            } else if (isShow){
                collapsingToolbarLayout.title = " " // needs to have a space.
                isShow = false
            }
        })

    }
}