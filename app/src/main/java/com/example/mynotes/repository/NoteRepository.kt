package com.example.mynotes.repository

import androidx.lifecycle.LiveData
import com.example.mynotes.database.NoteDao
import com.example.mynotes.model.Note

class NoteRepository(private val noteDao: NoteDao){

    suspend fun insertNote(note: Note){
        noteDao.insertNote(note)
    }
    suspend fun deleteNote(note: Note){
        noteDao.deleteNote(note)
    }
    suspend fun updateNote(note: Note){
        noteDao.updateNote(note)
    }

    fun getAllNotes(): LiveData<List<Note>>{
        return noteDao.getAllNotes()
    }

    fun searchNote(query: String?): LiveData<List<Note>>{
        return noteDao.searchNote(query)
    }
}