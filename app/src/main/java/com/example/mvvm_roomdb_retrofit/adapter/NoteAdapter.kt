package com.example.mvvm_roomdb_retrofit.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvm_roomdb_retrofit.R
import com.example.mvvm_roomdb_retrofit.database.Note

class NoteAdapter(
    val context: Context,
    val noteClickDeleteInterface: NoteClickDeleteInterface,
    val noteClickInterface: NoteClickInterface
) : RecyclerView.Adapter<NoteAdapter.ViewHolder>() {


    val allNotes = ArrayList<Note>()

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val noteTitle : TextView = itemView.findViewById(R.id.title)
        val noteText : TextView = itemView.findViewById<TextView>(R.id.note_text)
        val noteDate : TextView = itemView.findViewById<TextView>(R.id.note_last_updated)
        val noteDelete :ImageButton = itemView.findViewById<ImageButton>(R.id.delete_note)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView =
            LayoutInflater.from(context).inflate(R.layout.note_body_layout, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return allNotes.size
    }

    fun updateList(newList: List<Note>) {
        allNotes.clear()
        allNotes.addAll(newList)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.noteTitle.text = allNotes[position].noteTitle
        holder.noteText.text = allNotes[position].noteDescription
        holder.noteDate.text = allNotes[position].timeStamp

        holder.noteDelete.setOnClickListener {
            noteClickDeleteInterface.onDeleteIconClick(allNotes[position])
        }

        holder.itemView.setOnClickListener {
            noteClickInterface.onNoteClick(allNotes[position])
        }
    }
}

interface NoteClickInterface {
    fun onNoteClick(note: Note)
}

interface NoteClickDeleteInterface {
    fun onDeleteIconClick(note: Note)
}