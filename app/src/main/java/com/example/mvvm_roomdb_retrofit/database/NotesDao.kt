package com.example.mvvm_roomdb_retrofit.database


import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update


@Dao
interface NotesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(note: Note)

    @Delete
    suspend fun delete(note : Note)

    @Query("Select * from notesTable order by id ASC")
    fun getAllNotes():LiveData<List<Note>>

    @Update
    suspend fun update(note:Note)

    @Query("UPDATE notesTable SET title = :title, description = :note WHERE id = :id")
    suspend fun update_(id: Int?, title: String?, note: String?)
}



