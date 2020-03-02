package com.example.logicaapp

import android.util.Log
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        submitButton.setOnClickListener {
            var correctAnswers = 0;

            if (answerField1.text.toString() == "t") {
                correctAnswers += 1;
            }

            if (answerField2.text.toString() == "f") {
                correctAnswers += 1;
            }

            if (answerField3.text.toString() == "f") {
                correctAnswers += 1;
            }

            if (answerField4.text.toString() == "f") {
                correctAnswers += 1;
            }

            if (correctAnswers == 4) {
                Toast.makeText(this, "All answers are correct", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "There are " + correctAnswers + " correct answers", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
