package com.example.mds_dle_kotlin_todo

import android.app.AlertDialog
import android.content.Context
import android.util.Log
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.recyclerview.widget.RecyclerView
import com.example.mds_dle_kotlin_todo.models.Note
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import kotlinx.android.synthetic.main.item_list.view.*
import java.io.File
import java.lang.Exception
import java.security.AccessController.getContext


class NoteAdapter(
        private val notes: MutableList<Note>
) : RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {

    var bufferposition: Int = 0

    lateinit var con: Context;

    private lateinit var customAlertDialogView : View

    private lateinit var materialAlertDialogBuilder: MaterialAlertDialogBuilder

    fun setContext(context: Context): Context{
        con = context
        return context
    }

    class NoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind() {

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        return NoteViewHolder(
                LayoutInflater.from(parent.context).inflate(
                        R.layout.item_list,
                        parent,
                        false
                )
        )
    }

    fun addNote(note: Note, context: Context) {
        notes.add(note)
        notifyItemInserted(notes.size - 1)

        bufferposition += 1
        writeNote(context,notes, bufferposition)
    }

    fun deleteNote(index: Int) {
        notes.removeAt(index)
        notifyDataSetChanged()

        try {
           writeNote(con, notes, index) // rewrite file
        }catch(ex: Exception) { Log.d("TAG", "bug delete")}

    }

    fun updateNote(note: Note, index: Int, updateN: Note) {
        Log.d("TAG", note.title)
        note.title = updateN.title
        note.text = updateN.text
        notifyDataSetChanged()

        try {
            writeNote(con, notes, index) // rewrite file
        }catch(ex: Exception) { Log.d("TAG", "bug update")}
    }

    override fun getItemCount(): Int {
        return notes.size
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        var curNote = notes[position]

        holder.itemView.apply {
            tvNoteTitle.text = curNote.title
            tvNoteText.text = curNote.text
            ibDelete.setOnClickListener() {
                MaterialAlertDialogBuilder(context)
                        .setTitle("Confirmation of deletion")
                        .setMessage("Are you sure ?")
                        .setNegativeButton("decline") { dialog, which ->
                        }
                        .setPositiveButton("accept") { dialog, which ->
                            deleteNote(position)
                            Snackbar.make(it, "La note "+curNote.title+" à été supprimé.", Snackbar.LENGTH_LONG)
                                    .setAction(position.toString()) {
                                    }
                                    .show()
                        }
                        .show()
            }
            ibUpdate.setOnClickListener() {
                val mBuilder = AlertDialog.Builder(context)
                val mLayout  = LinearLayout(context)
                val mTvTitle  = TextView(context)
                val mTvText = TextView(context)
                val mEtTitle  = EditText(context)
                val mEtText = EditText(context)

                mTvTitle.text  = " Enter Title:"
                mTvText.text = " Enter Text:"
                mTvTitle.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16f)
                mTvText.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16f)
                mEtTitle.setSingleLine()
                mEtText.setSingleLine()
                mEtTitle.hint  = "Title"
                mEtText.hint = "Text"
                mLayout.orientation = LinearLayout.VERTICAL
                mLayout.addView(mTvTitle)
                mLayout.addView(mEtTitle)
                mLayout.addView(mTvText)
                mLayout.addView(mEtText)
                mLayout.setPadding(50, 40, 50, 10)

                mBuilder.setView(mLayout)

                //set positive button to alert dialog
                mBuilder.setPositiveButton("Done"){dialogInterface, i ->
                    //get text from edit texts
                    val updateTitle = mEtTitle.text.toString()
                    val updateText = mEtText.text.toString()
                    //set text to textView
                    val updateN = Note(updateTitle, updateText)
                    updateNote(curNote, position, updateN)
                    Snackbar.make(it, "La note "+curNote.title+" à été mise à jour.", Snackbar.LENGTH_LONG)
                            .setAction(position.toString()) {
                            }
                            .show()

                }
                //set neutral/cancel button
                mBuilder.setNeutralButton("Cancel"){dialogInterface, i ->
                    dialogInterface.dismiss()
                }
                //show dialog
                mBuilder.create().show()
            }
        }
    }
}