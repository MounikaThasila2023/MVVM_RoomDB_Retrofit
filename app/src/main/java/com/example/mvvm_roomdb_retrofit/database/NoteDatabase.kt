package com.example.mvvm_roomdb_retrofit.database


import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.utilities.DATABASE_NAME

@Database(entities = [Note::class], version = 1, exportSchema = false)
abstract class NoteDatabase : RoomDatabase(){
    abstract fun getNotesDao() : NotesDao


    companion object {
        private var instance: NoteDatabase? = null

        @Synchronized
        fun getDatabase(ctx: Context): NoteDatabase {
            if (instance == null)
                instance = Room.databaseBuilder(
                    ctx.applicationContext, NoteDatabase::class.java,
                    DATABASE_NAME
                )
                    .fallbackToDestructiveMigration()
                    .build()

            return instance!!
        }
        }


}