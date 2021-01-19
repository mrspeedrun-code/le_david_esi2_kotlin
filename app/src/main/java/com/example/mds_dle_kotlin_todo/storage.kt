package com.example.mds_dle_kotlin_todo

import android.content.Context
import android.util.Log
import com.example.mds_dle_kotlin_todo.models.Note
import com.google.android.material.snackbar.Snackbar
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.ObjectOutputStream

fun writeNote(context: Context, note: Note, position: Int){
    Log.d("TAG", "message$position")

    val fileOutput = context.openFileOutput("storage.txt",Context.MODE_PRIVATE)
    val outputStream = ObjectOutputStream(fileOutput)
    outputStream.writeObject(note.title + note.text)
    outputStream.close()
}

fun readNote(context: Context) {
    FileInputStream(context.getFileStreamPath("storage.txt")).use { stream ->
        val text = stream.bufferedReader().use {
            it.readText()
        }
        Log.d("TAG", "LOADED: $text")

        // transformer en objet note
        // liste d'objet note
    }
}

//var myExternalFile:File = File(getExternalFilesDir(filepath), "storage.txt")
/*
try {
    //val fileOutPutStream = FileOutputStream(myExternalFile)
    val fileBody = "text"

    var fileContext: Context

    val fileOutPutStream = FileOutputStream(parent.context.openFileOutput("storage.txt", Context.MODE_PRIVATE))
    fileOutPutStream.write(fileBody.toByteArray())
    fileOutPutStream.close()
} catch (e: IOException) {
    e.printStackTrace()
}

 */