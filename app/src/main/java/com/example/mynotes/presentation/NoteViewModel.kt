package com.example.mynotes.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.Query
import com.example.mynotes.model.Note
import com.example.mynotes.repository.NoteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NoteViewModel(application: Application, private val noteRepository: NoteRepository): AndroidViewModel(application) {

    fun addNote(note: Note) =
        viewModelScope.launch(Dispatchers.IO) {
            noteRepository.insertNote(note)
        }

    fun updateNote(note: Note) =
        viewModelScope.launch(Dispatchers.IO) {
            noteRepository.updateNote(note)
        }

    fun deleteNote(note: Note) =
        viewModelScope.launch(Dispatchers.IO) {
            noteRepository.deleteNote(note)
        }

    fun getAllNotes() = noteRepository.getAllNotes()

    fun searchNote(query: String?) = noteRepository.searchNote(query)
}