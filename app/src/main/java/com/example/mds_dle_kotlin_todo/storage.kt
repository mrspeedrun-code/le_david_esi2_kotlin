package com.example.mds_dle_kotlin_todo

import android.content.Context
import android.os.Handler
import android.util.Log
import com.example.mds_dle_kotlin_todo.models.Note
import com.google.android.material.snackbar.Snackbar
import java.io.*
import java.lang.Exception

fun writeNote(context: Context, notes: MutableList<Note>, position: Int){
    Log.d("TAG", "message$position")
    //val fileBody = note.title + ',' + note.text
    val fileBody = notes

    try {
        context.openFileOutput("storage.txt",Context.MODE_PRIVATE).use {output ->
            var rewrite = ""

            for (note in notes) {
                Log.d("TAG",">$note ")
                rewrite = note.title + ',' + note.text + "\n"
                output.write (rewrite.toByteArray ())
            }
        }
    } catch (ex: Exception) {}

}

fun readNote(context: Context): MutableList<Note> {
    val lineList = mutableListOf<String>()
    val reNoteList = mutableListOf<Note>()
0
    try {
        FileInputStream(context.getFileStreamPath("storage.txt")).use { stream ->
            val reader = stream.bufferedReader()
            reader.useLines {
                lines -> lines.forEach {
                    lineList.add(it)
                }
            }

            lineList.forEach{
                val line = it.split(",")
                val tab = line[0]
                val tab2 = line[1]
                val note = Note(tab, tab2)
                reNoteList.add(note)
            }
        }
    } catch (ex: Exception) {}

    return reNoteList
}
