package com.example.mynotes

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.mynotes.database.NoteDao
import com.example.mynotes.database.NoteDatabase
import com.example.mynotes.presentation.NoteViewModel
import com.example.mynotes.presentation.NoteViewModelFactory
import com.example.mynotes.repository.NoteRepository

class MainActivity : AppCompatActivity() {

    lateinit var noteViewModel: NoteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupViewModel()
    }

    private fun setupViewModel() {
        val noteRepository = NoteRepository(NoteDao)
        val viewModelProviderFactory =  NoteViewModelFactory(application, noteRepository)
        noteViewModel = ViewModelProvider(this, viewModelProviderFactory)[NoteViewModel::class.java]
    }
}