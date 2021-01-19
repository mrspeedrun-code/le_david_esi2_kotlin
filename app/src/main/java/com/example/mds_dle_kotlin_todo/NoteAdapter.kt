package com.example.mds_dle_kotlin_todo

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mds_dle_kotlin_todo.models.Note
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.item_list.view.*
import java.io.File


class NoteAdapter(
        private val notes: MutableList<Note>
) : RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {

    var bufferposition: Int = 0

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
        writeNote(context,note, bufferposition)
    }

    fun deleteNote(index: Int) {
        notes.removeAt(index)
        notifyDataSetChanged()
    }

    fun updateNote(note: Note, index: Int) {
        notes.removeAt(index)
        notes.add(note)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return notes.size
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val curNote = notes[position]

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
                updateNote(curNote, position)
                Snackbar.make(it, "La note "+curNote.title+" à été mise à jour.", Snackbar.LENGTH_LONG)
                        .setAction(position.toString()) {
                        }
                        .show()
            }
        }
    }


}