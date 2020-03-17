package com.example.studentportal

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_create_portal.*

const val EXTRA_PORTAL = "EXTRA_PORTAL"

class CreatePortalActivity : AppCompatActivity() {

    private fun initViews() {
        btnAdd.setOnClickListener { onSaveClick() }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_portal)
        initViews()
    }

    private fun onSaveClick() {
        if (teTitle.text.toString().isNotBlank() && teUrl.text.toString().isNotBlank()) {
            val portal = Portal(teTitle.text.toString(), teUrl.text.toString())
            val resultIntent = Intent()

            resultIntent.putExtra(EXTRA_PORTAL, portal)
            setResult(Activity.RESULT_OK, resultIntent)

            finish()
        } else {
            Toast.makeText(this,"Fill in both fields", Toast.LENGTH_SHORT).show()
        }
    }


}