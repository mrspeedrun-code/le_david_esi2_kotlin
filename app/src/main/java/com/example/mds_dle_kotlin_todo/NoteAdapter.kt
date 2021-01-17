package com.example.mds_dle_kotlin_todo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mds_dle_kotlin_todo.models.Note
import com.example.mds_dle_kotlin_todo.MainActivity.*
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.android.synthetic.main.item_list.view.*


class NoteAdapter(
        private val notes: MutableList<Note>
) : RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {

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

    fun addNote(note: Note) {
        notes.add(note)
        notifyItemInserted(notes.size - 1)
    }

    fun deleteNote(index: Int) {
        notes.removeAt(index)
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
                deleteNote(position)
                Snackbar.make(it, "La note "+curNote.title+" à été supprimé.", Snackbar.LENGTH_LONG)
                        .setAction(position.toString()) {
                        }
                        .show()
            }
        }
    }


}