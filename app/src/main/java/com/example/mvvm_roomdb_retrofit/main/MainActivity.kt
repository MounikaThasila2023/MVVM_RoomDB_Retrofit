package com.example.mvvm_roomdb_retrofit.main

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mvvm_roomdb_retrofit.adapter.NoteAdapter
import com.example.mvvm_roomdb_retrofit.adapter.NoteClickDeleteInterface
import com.example.mvvm_roomdb_retrofit.adapter.NoteClickInterface
import com.example.mvvm_roomdb_retrofit.database.Note
import com.example.mvvm_roomdb_retrofit.databinding.ActivityMainBinding
import com.example.mvvm_roomdb_retrofit.viewmodel.NoteViewModel


class MainActivity : AppCompatActivity(), NoteClickInterface, NoteClickDeleteInterface {

    lateinit var binding: ActivityMainBinding

    lateinit var viewModel: NoteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Log.e("Hello", "Hiiiii")
        binding.rvNotesList.layoutManager = LinearLayoutManager(this)

        val noteAdapter = NoteAdapter(this, this, this)

        binding.rvNotesList.adapter = noteAdapter

        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        )[NoteViewModel::class.java]


        viewModel.allNotes.observe(this, Observer { list ->
            list?.let {
                noteAdapter.updateList(it)
            }
        })

    }

    override fun onNoteClick(note: Note) {
        val intent = Intent(this@MainActivity, AddEditNoteActivity::class.java)
        intent.putExtra("noteType", "Edit")
        intent.putExtra("noteTitle", note.noteTitle)
        intent.putExtra("noteDescription", note.noteDescription)
        intent.putExtra("noteDate", note.timeStamp)
        intent.putExtra("noteId", note.id)
        startActivity(intent)
        this.finish()
    }

    override fun onDeleteIconClick(note: Note) {
        viewModel.deleteNote(note)
        Toast.makeText(this, "${note.noteTitle} Deleted", Toast.LENGTH_SHORT).show()
    }
}