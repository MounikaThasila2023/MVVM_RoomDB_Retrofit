package com.example.mvvm_roomdb_retrofit.repositories

import androidx.lifecycle.LiveData
import com.example.mvvm_roomdb_retrofit.database.Note
import com.example.mvvm_roomdb_retrofit.database.NotesDao

class NoteRepository (val notesDao: NotesDao) {

    fun getAllNotes():  LiveData<List<Note>> = notesDao.getAllNotes()

    suspend fun insert(note: Note) {
        notesDao.insert(note)
    }

    suspend fun delete(note: Note) {
        notesDao.delete(note)
    }

    suspend fun update(note: Note) {
        notesDao.update(note)
    }

}