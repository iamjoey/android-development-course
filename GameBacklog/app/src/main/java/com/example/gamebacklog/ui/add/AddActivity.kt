package com.example.gamebacklog.ui.add

import java.util.*
import android.os.Bundle
import java.lang.Exception
import android.app.Activity
import android.widget.Toast
import android.content.Intent
import com.example.gamebacklog.R
import com.example.gamebacklog.model.Game
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.content_add.*
import kotlinx.android.synthetic.main.activity_add.*

const val GAME_VIEW ="GAME_VIEW"

class AddActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)
        setSupportActionBar(toolbar)

        fab.setOnClickListener {
            onSaveClick()
        }
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun onSaveClick() {
        if (
            etTitle.text.toString().isNotBlank() &&
            etPlatform.text.toString().isNotBlank() &&
            etDay.text.toString().isNotBlank() &&
            etMonth.text.toString().isNotBlank() &&
            etYear.text.toString().isNotBlank()
        ) {
            try {
                val cal = Calendar.getInstance()
                val day = etDay.text.toString().toInt()
                val month = etMonth.text.toString().toInt()
                val year = etYear.text.toString().toInt()

                if (month !in 1..12 || day !in 1..31) throw Exception()

                cal.set(year, month - 1, day)

                val game = Game(etTitle.text.toString(), etPlatform.text.toString(), cal.time)
                val resultIntent = Intent()

                resultIntent.putExtra(GAME_VIEW, game)
                setResult(Activity.RESULT_OK, resultIntent)

                finish()
            } catch(e: Exception) {
                Toast.makeText(this, "Invalid date", Toast.LENGTH_SHORT).show()
            }
        } else {
            Toast.makeText(this,"The game cannot be empty", Toast.LENGTH_SHORT).show()
        }
    }

}