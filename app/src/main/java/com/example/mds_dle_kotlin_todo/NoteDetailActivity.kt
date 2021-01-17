package com.example.mds_dle_kotlin_todo

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.note_detail.*


class NoteDetailActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.note_detail)

        val actionBar = supportActionBar

        actionBar!!.title = "Edition de note"

        addBtn.setOnClickListener{
            val noteTitle = etNoteTitle.text.toString()
            val noteText = etNoteText.text.toString()

            val resultIntent = Intent()
            resultIntent.putExtra("noteTitle", noteTitle)
            resultIntent.putExtra("noteText", noteText)

            setResult(RESULT_OK, resultIntent)
            finish()
        }

    }

}