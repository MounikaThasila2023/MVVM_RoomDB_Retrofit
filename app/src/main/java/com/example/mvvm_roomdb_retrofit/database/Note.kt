package com.example.mvvm_roomdb_retrofit.database


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "notesTable")
data class Note(
    @PrimaryKey(autoGenerate = false)
    val id: Int?,
    @ColumnInfo(name = "title")
    val noteTitle: String,
    @ColumnInfo(name = "description")
    val noteDescription: String,
    @ColumnInfo(name = "timeStamp")
    val timeStamp: String
)



