package com.example.mds_dle_kotlin_todo

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mds_dle_kotlin_todo.models.Note
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item_list.*

class NoteListActivity : AppCompatActivity() {
    lateinit var noteAdapter: NoteAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        noteAdapter = NoteAdapter(mutableListOf())

        rvNoteItems.adapter = noteAdapter
        rvNoteItems.layoutManager = LinearLayoutManager(this)

        fabAddNote.setOnClickListener{
            Intent(this, NoteDetailActivity::class.java).also {
                //startActivity(it)
                startActivityForResult(it, 1)
            }
        }


        /*
        ibDelete.setOnClickListener{
            Snackbar.make(it, "Text label", Snackbar.LENGTH_LONG)
                    .setAction("Action") {
                        // Responds to click on the action
                    }
                    .show()
            //noteAdapter.deleteNote(1)
        }


         */

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        val noteTitle = data?.getStringExtra("noteTitle")
        val noteText = data?.getStringExtra("noteText")

        val note = Note(noteTitle.toString(), noteText.toString())

        noteAdapter.addNote(note)


        //val toast = Toast.makeText(this, data?.getStringExtra("noteText"), Toast.LENGTH_LONG)
        //toast.show()
    }
}