package com.example.mds_dle_kotlin_todo

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mds_dle_kotlin_todo.models.Note
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item_list.*
import java.lang.Exception

class NoteListActivity : AppCompatActivity() {
    lateinit var noteAdapter: NoteAdapter

    lateinit var contextNote: Context

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        lateinit var load : MutableList<Note>
        load = readNote(this)

        noteAdapter = NoteAdapter(mutableListOf())

        rvNoteItems.adapter = noteAdapter
        rvNoteItems.layoutManager = LinearLayoutManager(this)

        if (load !== null) {
            for (l in load) {
                noteAdapter.addNote(l, this)
            }
        }

        contextNote = this
        noteAdapter.setContext(contextNote)

        fabAddNote.setOnClickListener{
            Intent(this, NoteDetailActivity::class.java).also {
                //startActivity(it)
                startActivityForResult(it, 1)
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        val noteTitle = data?.getStringExtra("noteTitle")
        val noteText = data?.getStringExtra("noteText")

        val note = Note(noteTitle.toString(), noteText.toString())

        noteAdapter.addNote(note, this)
    }
}