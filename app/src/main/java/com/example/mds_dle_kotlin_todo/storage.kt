package com.example.mds_dle_kotlin_todo

import android.content.Context
import com.example.mds_dle_kotlin_todo.models.Note
import java.io.ObjectOutputStream

fun writeNote(context: Context, note: Note){
    val fileOutput = context.openFileOutput("storage.txt",Context.MODE_PRIVATE)
    val outputStream = ObjectOutputStream(fileOutput)
    outputStream.writeObject(note)
    outputStream.close()
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