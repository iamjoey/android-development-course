package com.example.numbertrivia.ui

import android.os.Bundle
import android.widget.Toast
import com.example.numbertrivia.R
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.appcompat.app.AppCompatActivity

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        initViews()
        initViewModel()
    }

    private fun initViews() {
        fab.setOnClickListener {
            viewModel.getRandomTrivia() // Get a random number trivia when the fab is clicked
        }
    }

    private fun initViewModel() {
        // Initialize the MainActivityViewModel
        viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)

        // Observe the trivia object
        viewModel.trivia.observe(this, Observer {
            tvTrivia.text = it?.text
        })

        viewModel.error.observe(this, Observer {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        })
    }

}