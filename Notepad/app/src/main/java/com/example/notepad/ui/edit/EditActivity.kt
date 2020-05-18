package com.example.notepad.ui.edit

import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.notepad.R

import kotlinx.android.synthetic.main.activity_edit.*
import kotlinx.android.synthetic.main.content_edit.*
import java.util.*

class EditActivity : AppCompatActivity() {

    private lateinit var editActivityViewModel: EditActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        initViews()
        initViewModel()
    }

    private fun initViews() {
        fab.setOnClickListener {
            editActivityViewModel.note.value?.apply {
                title = etTitle.text.toString()
                lastUpdated = Date()
                text = etNote.text.toString()
            }

            editActivityViewModel.updateNote()
        }
    }

    private fun initViewModel() {
        editActivityViewModel = ViewModelProvider(this).get(EditActivityViewModel::class.java)
        editActivityViewModel.note.value = intent.extras?.getParcelable(EXTRA_NOTE)!!

        editActivityViewModel.note.observe(this, androidx.lifecycle.Observer {
                note ->
            if (note != null) {
                etTitle.setText(note.title)
                etNote.setText(note.text)
            }
        })

        editActivityViewModel.error.observe(this, androidx.lifecycle.Observer {
                error ->
            Toast.makeText(this, error, Toast.LENGTH_SHORT)
        })

        editActivityViewModel.success.observe(this, androidx.lifecycle.Observer {
                success ->
            if (success!!) finish()
        })
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    companion object {
        const val EXTRA_NOTE = "EXTRA_NOTE"
    }

}