package com.example.mynotes.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.mynotes.model.Note

@Database(entities = [Note::class], version = 1, exportSchema = false)
abstract class NoteDatabase: RoomDatabase(){

    abstract fun getNoteDao(): NoteDao

    companion object{
        @Volatile
        private var INSTANCE: NoteDatabase? = null

        fun getDatabase(context: Context): NoteDatabase{
            val tempInstance = INSTANCE
            if(tempInstance != null) return tempInstance
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    NoteDatabase::class.java,
                    "Notes_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}