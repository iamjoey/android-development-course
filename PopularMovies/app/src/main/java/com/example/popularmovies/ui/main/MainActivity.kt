package com.example.popularmovies.ui.main

import kotlin.math.floor
import android.view.View
import android.os.Bundle
import android.widget.Toast
import android.content.Intent
import androidx.lifecycle.Observer
import com.example.popularmovies.R
import android.view.ViewTreeObserver
import androidx.lifecycle.ViewModelProvider
import com.example.popularmovies.model.Movie
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import androidx.recyclerview.widget.GridLayoutManager
import com.example.popularmovies.ui.detail.DetailsActivity

const val EXTRA_MOVIE = "EXTRA_MOVIE"

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: MainActivityViewModel
    private val movies = arrayListOf<Movie>()
    private val movieAdapter = MovieAdapter(movies) {movieItem -> onMovieClick(movieItem) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViews()
        initViewModel()
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

        rvMovies.adapter = movieAdapter

        btnSubmit.setOnClickListener {
            viewModel.getMovies(etYear.text.toString())
        }
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)

        viewModel.moviesPage.observe(this, Observer {
            movies.clear()
            movies.addAll(it.movies)
            movieAdapter.notifyDataSetChanged()
        })

        viewModel.error.observe(this, Observer {
            Toast.makeText(this, it, Toast.LENGTH_LONG).show()
        })

        viewModel.progressBarStatus.observe(this, Observer {
            if (it) progressBar.visibility = View.VISIBLE
            else progressBar.visibility = View.GONE
        })
    }

    private fun onMovieClick(movieItem: Movie) {
        val intent = Intent(this, DetailsActivity::class.java)
        intent.putExtra(EXTRA_MOVIE, movieItem)
        startActivityForResult(intent, 100)
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