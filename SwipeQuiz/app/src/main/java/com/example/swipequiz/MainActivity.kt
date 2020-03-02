package com.example.swipequiz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {

    var questions: ArrayList<Question> = arrayListOf()

    var questionAdapter: QuestionAdapter = QuestionAdapter(questions)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViews()
        enableSwipe()
    }

    private fun initViews() {
        rvQuestions.adapter = questionAdapter
        rvQuestions.layoutManager =
            LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL, false)

        for (i in Question.QUESTION_TEXTS.indices) {
            questions.add(Question(Question.QUESTION_TEXTS[i], Question.QUESTION_ANSWERS[i]))
        }

        questionAdapter.notifyDataSetChanged()
    }

    private fun enableSwipe() {
        val simpleItemTouchCallback =
            object :
                ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {

                override fun onMove(
                    recyclerView: RecyclerView,
                    viewHolder: RecyclerView.ViewHolder,
                    target: RecyclerView.ViewHolder
                ): Boolean {
                    return false
                }

                override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                    val position = viewHolder.adapterPosition
                    val deletedQuestion = questions[position]
                    val response: String
//                    questionAdapter.removeItem(position)

                    if (direction == ItemTouchHelper.LEFT) {
                        when(deletedQuestion.answer) {

                            true -> {
                                response = "Correct! the answer was: " + deletedQuestion.answer
                            }

                            false -> {
                                response = "Incorrect! Question added back to the list"
//                                questionAdapter.addItem(deletedQuestion)
                            }
                        }
                    } else {
                        when(deletedQuestion.answer) {

                            true -> {
                                response =  "Incorrect! Question added back to the list"
//                                questionAdapter.addItem(deletedQuestion)
                            }

                            false -> {
                                response = "Correct! the answer was: " + deletedQuestion.answer
                            }
                        }
                    }

                    val snackbar = Snackbar.make(
                        rootConstraint,
                        response,
                        Snackbar.LENGTH_LONG
                    )
                    snackbar.show()
                }

            }

        val itemTouchHelper = ItemTouchHelper(simpleItemTouchCallback)
        itemTouchHelper.attachToRecyclerView(rvQuestions)
    }
}
