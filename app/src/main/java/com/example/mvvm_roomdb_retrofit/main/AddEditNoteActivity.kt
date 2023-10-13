package com.example.mvvm_roomdb_retrofit.main

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.mvvm_roomdb_retrofit.R
import com.example.mvvm_roomdb_retrofit.database.Note
import com.example.mvvm_roomdb_retrofit.databinding.ActivityAddEditNoteBinding
import com.example.mvvm_roomdb_retrofit.viewmodel.NoteViewModel
import java.text.SimpleDateFormat
import java.util.Date

class AddEditNoteActivity : AppCompatActivity() {

     lateinit var binding: ActivityAddEditNoteBinding

    lateinit var oldNote: Note
    lateinit var viewModel: NoteViewModel
    var noteId = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddEditNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        )[NoteViewModel::class.java]

        val noteType = intent.getStringExtra("noteType")
        if (noteType.equals("Edit")) {

            val noteTitle = intent.getStringExtra("noteTitle")
            val noteDescription = intent.getStringExtra("noteDescription")

            noteId = intent.getIntExtra("noteId", -1)
            binding.idBtn.text = "Update Note"
            binding.idEdtNoteName.setText(noteTitle)
            binding.idEdtNoteDesc.setText(noteDescription)
        } else {
            binding.idBtn.text = "Save Note"
        }

        binding.idBtn.setOnClickListener {

            val noteTitle = binding.idEdtNoteName.text.toString()
            val noteDescription = binding.idEdtNoteDesc.text.toString()

            if (noteType.equals("Edit")) {
                if (noteTitle.isNotEmpty() && noteDescription.isNotEmpty()) {
                    val sdf = SimpleDateFormat("dd MMM, yyyy - HH:mm")

                    val currentDateAndTime: String = sdf.format(Date())
                    val updatedNote = Note(oldNote.id,noteTitle, noteDescription, currentDateAndTime)
                    viewModel.updateNote(updatedNote)
                    Toast.makeText(this, "Note Updated..", Toast.LENGTH_LONG).show()
                }
            } else {
                if (noteTitle.isNotEmpty() && noteDescription.isNotEmpty()) {
                    val sdf = SimpleDateFormat("dd MMM, yyyy - HH:mm")
                    val currentDateAndTime: String = sdf.format(Date())

                    viewModel.insertNote(Note(oldNote.id,noteTitle, noteDescription, currentDateAndTime))
                    Toast.makeText(this, "$noteTitle Added", Toast.LENGTH_LONG).show()
                }
            }
           
            startActivity(Intent(applicationContext, MainActivity::class.java))
            this.finish()
        }

    }
}